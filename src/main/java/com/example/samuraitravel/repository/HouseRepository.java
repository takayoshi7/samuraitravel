package com.example.samuraitravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.House;

public interface HouseRepository extends JpaRepository<House, Integer> {
    // indAll()：テーブル内のすべてのエンティティを取得する
    // getReferenceById(id)：指定したidのエンティティを取得する
    // save(エンティティ)：指定したエンティティを保存または更新する
    // delete(エンティティ)：指定したエンティティを削除する
    // deleteById(id)：指定したidのエンティティを削除する
}