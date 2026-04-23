import os
import re

path = 'app/src/main/java/com/elv8/crisisos/data/remote/mesh/MeshMessenger.kt'
with open(path, 'r', encoding='utf-8') as f:
    text = f.read()

# Add imports
imports_to_add = [
    'import com.elv8.crisisos.domain.model.ChatMessage',
    'import com.elv8.crisisos.core.network.mesh.DomainSendResult',
    'import com.elv8.crisisos.data.dto.PacketFactory'
]
for imp in imports_to_add:
    if imp not in text:
        text = text.replace('import android.content.Context', f'import android.content.Context\n{imp}')

# Add the new method inside MeshMessenger
method_str = '''
    override suspend fun sendChatMessage(message: ChatMessage): DomainSendResult {
        val chatPayload = ChatPayload(content = message.content, messageId = message.id)
        val packet = PacketFactory.buildChatPacket(
            senderId = message.senderId,
            senderAlias = message.senderAlias,
            payload = chatPayload
        )

        return when (send(packet)) {
            is SendResult.Sent -> DomainSendResult.Sent
            is SendResult.Queued -> DomainSendResult.Queued
            is SendResult.Failed -> DomainSendResult.Failed
        }
    }
'''
if 'sendChatMessage' not in text:
    text = re.sub(r'(class MeshMessenger.*?\{)', r'\1\n' + method_str, text, count=1, flags=re.DOTALL)

with open(path, 'w', encoding='utf-8') as f:
    f.write(text)
print("MeshMessenger updated.")
