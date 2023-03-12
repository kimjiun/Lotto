package com.ggidid.lotto.database.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.ggidid.lotto.model.LottoInfo
import com.ggidid.lotto.model.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRemote(list: List<RemoteKey>)

    @Query("SELECT * FROM RemoteKey WHERE repoId = :id")
    fun getRemoteKeys(id:String) : RemoteKey

    @Query("DELETE FROM RemoteKey")
    fun clearAll()
}