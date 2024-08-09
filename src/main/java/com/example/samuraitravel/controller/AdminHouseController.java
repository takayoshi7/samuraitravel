package com.example.samuraitravel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.form.HouseEditForm;
import com.example.samuraitravel.form.HouseRegisterForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.service.HouseService;

@Controller
@RequestMapping("/admin/houses")
public class AdminHouseController {
    // final宣言により、コンストラクタで初期化された後は変更されない
    private final HouseRepository houseRepository;
    private final HouseService houseService;

    @Autowired // コンストラクタが1つしかない場合は省略可能
    // コンストラクタインジェクション（依存性の注入（DI））
    public AdminHouseController(HouseRepository houseRepository, HouseService houseService) {
        this.houseRepository = houseRepository;
        this.houseService = houseService;
    }

    @GetMapping
    // PageableDefaultでページ情報のデフォルト値を設定できる（標準のデフォルト値はあるが、なるべく明示的に設定する）
    public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam(name = "keyword", required = false) String keyword) {
        // houseテーブルのデータをPage型として全取得
        Page<House> housePage = houseRepository.findAll(pageable);

        if (keyword != null && !keyword.isEmpty()) {
            // 検索ワードがあれば部分一致検索
            housePage = houseRepository.findByNameLike("%" + keyword + "%", pageable);
        } else {
            // 検索ワードが無ければ全件取得
            housePage = houseRepository.findAll(pageable);
        }

        model.addAttribute("housePage", housePage);
        model.addAttribute("keyword", keyword);

        return "admin/houses/index";
    }

    @GetMapping("/{id}")
    // PathVariableで、URLの一部を変数のように使える
    public String show(@PathVariable(name = "id") Integer id, Model model) {
        // 指定したidのエンティティを取得する
        House house = houseRepository.getReferenceById(id);

        model.addAttribute("house", house);

        return "admin/houses/show";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("houseRegisterForm", new HouseRegisterForm());
        return "admin/houses/register";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Validated HouseRegisterForm houseRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/houses/register";
        }

        houseService.create(houseRegisterForm);
        // addFlashAttributeは、リダイレクトの直後に1回限り利用するデータを渡す
        redirectAttributes.addFlashAttribute("successMessage", "民宿を登録しました。");

        return "redirect:/admin/houses";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable(name = "id") Integer id, Model model) {
        House house = houseRepository.getReferenceById(id);
        // 民宿画像のファイル名を取得する
        String imageName = house.getImageName();
        // フォームクラスをインスタンス化する
        HouseEditForm houseEditForm = new HouseEditForm(house.getId(), house.getName(), null, house.getDescription(), house.getPrice(), house.getCapacity(), house.getPostalCode(), house.getAddress(), house.getPhoneNumber());
        // 民宿画像のファイル名をビューに渡す
        model.addAttribute("imageName", imageName);
        // 生成したインスタンスをビューに渡す
        model.addAttribute("houseEditForm", houseEditForm);

        return "admin/houses/edit";
    }

    @PostMapping("/{id}/update")
    public String update(@ModelAttribute @Validated HouseEditForm houseEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/houses/edit";
        }

        houseService.update(houseEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "民宿情報を編集しました。");

        return "redirect:/admin/houses";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        houseRepository.deleteById(id);

        redirectAttributes.addFlashAttribute("successMessage", "民宿を削除しました。");

        return "redirect:/admin/houses";
    }

}
