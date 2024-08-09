package com.example.samuraitravel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 
 import com.example.samuraitravel.entity.House;
 import com.example.samuraitravel.repository.HouseRepository;
 
 @Controller
 @RequestMapping("/admin/houses")
public class AdminHouseController {
    // final宣言により、コンストラクタで初期化された後は変更されない
    private final HouseRepository houseRepository;

    @Autowired // コンストラクタが1つしかない場合は省略可能
    // コンストラクタインジェクション（依存性の注入（DI））
    public AdminHouseController(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @GetMapping
    // PageableDefaultでページ情報のデフォルト値を設定できる（標準のデフォルト値はあるが、なるべく明示的に設定する）
    public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
        // houseテーブルのデータをPage型として全取得
        Page<House> housePage = houseRepository.findAll(pageable);

        model.addAttribute("housePage", housePage);

        return "admin/houses/index";
    }
}
