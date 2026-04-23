package com.elv8.crisisos.di

import android.content.Context
import com.elv8.crisisos.core.event.EventBus
import com.elv8.crisisos.core.event.EventLogger
import com.elv8.crisisos.core.network.mesh.IMeshConnectionManager
import com.elv8.crisisos.core.network.mesh.IMeshMessenger
import dagger.Module
import dagger.Provides
import com.elv8.crisisos.core.permissions.MeshPermissionManager
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton
import com.elv8.crisisos.data.remote.mesh.MeshConnectionManager
import com.elv8.crisisos.data.remote.mesh.MeshHealthMonitor
import com.elv8.crisisos.data.remote.mesh.MeshMessenger
import com.elv8.crisisos.data.local.dao.OutboxDao
import com.elv8.crisisos.domain.repository.OutboxRepository
import androidx.work.WorkManager

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMeshPermissionManager(@ApplicationContext context: Context): MeshPermissionManager {
        return MeshPermissionManager(context)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideEventBus(): EventBus {
        return EventBus()
    }

    @Provides
    @Singleton
    fun provideMockPeerInjector(
        peerDao: com.elv8.crisisos.data.local.dao.PeerDao,
        scope: CoroutineScope
    ): com.elv8.crisisos.core.debug.MockPeerInjector {
        return com.elv8.crisisos.core.debug.MockPeerInjector(peerDao, scope)
    }

    @Provides
    @Singleton
    fun provideMeshRecoveryManager(
        connectionManager: IMeshConnectionManager,
        permissionManager: com.elv8.crisisos.core.permissions.MeshPermissionManager,
        eventBus: com.elv8.crisisos.core.event.EventBus,
        scope: kotlinx.coroutines.CoroutineScope,
        @dagger.hilt.android.qualifiers.ApplicationContext context: android.content.Context
    ): com.elv8.crisisos.core.recovery.MeshRecoveryManager {
        return com.elv8.crisisos.core.recovery.MeshRecoveryManager(connectionManager, permissionManager, eventBus, scope, context)
    }

    @Provides
    @Singleton
    fun provideMeshDiagnostics(
        connectionManager: IMeshConnectionManager,
        permissionManager: com.elv8.crisisos.core.permissions.MeshPermissionManager,
        peerDao: com.elv8.crisisos.data.local.dao.PeerDao,
        scope: CoroutineScope
    ): com.elv8.crisisos.core.debug.MeshDiagnostics {
        return com.elv8.crisisos.core.debug.MeshDiagnostics(
            connectionManager, permissionManager, peerDao, scope
        )
    }

    @Provides
    @Singleton
    fun provideMeshConnectionManager(
        impl: MeshConnectionManager
    ): IMeshConnectionManager {
        return impl
    }

    @Provides
    @Singleton
    fun provideMeshMessenger(
        @ApplicationContext context: Context,
        connectionManager: IMeshConnectionManager,
        outboxRepository: OutboxRepository,
        eventBus: EventBus,
        notificationBus: com.elv8.crisisos.core.notification.NotificationEventBus,
        chatDao: com.elv8.crisisos.data.local.dao.ChatDao,
        chatThreadDao: com.elv8.crisisos.data.local.dao.ChatThreadDao,
        mediaRepository: com.elv8.crisisos.domain.repository.MediaRepository,
        mediaDao: com.elv8.crisisos.data.local.dao.MediaDao,
        fileManager: com.elv8.crisisos.device.media.MediaFileManager,
        scope: CoroutineScope
    ): IMeshMessenger {
        return MeshMessenger(
            context = context,
            connectionManager = connectionManager,
            outboxRepository = outboxRepository,
            eventBus = eventBus,
            notificationBus = notificationBus,
            chatDao = chatDao,
            chatThreadDao = chatThreadDao,
            mediaRepository = mediaRepository,
            mediaDao = mediaDao,
            fileManager = fileManager,
            scope = scope
        )
    }

    @Provides
    @Singleton
    fun provideMeshHealthMonitor(
        outboxRepository: OutboxRepository,
        outboxDao: OutboxDao,
        connectionManager: IMeshConnectionManager,
        scope: CoroutineScope
    ): MeshHealthMonitor {
        return MeshHealthMonitor(outboxRepository, outboxDao, connectionManager, scope)
    }

    @Provides
    fun provideEventLogger(
        bus: EventBus,
        scope: CoroutineScope
    ): EventLogger {
        return EventLogger(bus, scope)
    }

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }
}

