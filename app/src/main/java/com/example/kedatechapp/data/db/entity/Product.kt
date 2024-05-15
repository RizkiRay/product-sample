package com.example.kedatechapp.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    @ColumnInfo val name: String,
    @ColumnInfo val picUrl: String,
    @ColumnInfo val desc: String,
    @ColumnInfo val price: Long,
    @ColumnInfo val isFavorite: Boolean
) : Parcelable