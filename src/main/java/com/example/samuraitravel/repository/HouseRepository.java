package com.example.samuraitravel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.House;

// インターフェースにはメソッドの名前、引数の型、戻り値の型のみを定義
// メソッドの具体的な処理内容は書かない
public interface HouseRepository extends JpaRepository<House, Integer> {
    // indAll()：テーブル内のすべてのエンティティを取得する
    // getReferenceById(id)：指定したidのエンティティを取得する
    // save(エンティティ)：指定したエンティティを保存または更新する
    // delete(エンティティ)：指定したエンティティを削除する
    // deleteById(id)：指定したidのエンティティを削除する


    public Page<House> findByNameLike(String keyword, Pageable pageable);

    public Page<House> findByNameLikeOrAddressLikeOrderByCreatedAtDesc(String nameKeyword, String addressKeyword, Pageable pageable);
    public Page<House> findByNameLikeOrAddressLikeOrderByPriceAsc(String nameKeyword, String addressKeyword, Pageable pageable);
    public Page<House> findByAddressLikeOrderByCreatedAtDesc(String area, Pageable pageable);
    public Page<House> findByAddressLikeOrderByPriceAsc(String area, Pageable pageable);
    public Page<House> findByPriceLessThanEqualOrderByCreatedAtDesc(Integer price, Pageable pageable);
    public Page<House> findByPriceLessThanEqualOrderByPriceAsc(Integer price, Pageable pageable);
    public Page<House> findAllByOrderByCreatedAtDesc(Pageable pageable);
    public Page<House> findAllByOrderByPriceAsc(Pageable pageable);
}