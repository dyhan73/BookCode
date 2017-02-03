package com.ensoa.order.controller.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ensoa.order.domain.Product;
import com.ensoa.order.model.ProductModel;
import com.ensoa.order.service.ProductService;

@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="edit.do", method=RequestMethod.GET)
	public String edit(Model model) {
		ProductModel product = new ProductModel();
		model.addAttribute("product", product);
		return "edit";
	}
	@RequestMapping(value="/edit.do", method=RequestMethod.POST)	
	public String add(@Valid @ModelAttribute("product") ProductModel model,
			BindingResult bindingResult,
			@RequestParam(value="image", required=false) MultipartFile image,
			HttpServletRequest request) {
		if(bindingResult.hasErrors())
			return "edit";
		try {
			if(!image.isEmpty()) {
				if(!image.getContentType().equals("image/jpeg")) {
					throw new ImageUploadException("JPEG 이미지만 선택해주세요.");
				}
				String webRootPath = request.getSession().getServletContext().getRealPath("/");
				String filePath = webRootPath +"resources/" + image.getOriginalFilename();
				File file = new File(filePath);
				FileUtils.writeByteArrayToFile(file, image.getBytes());
				logger.info("업로드 파일 : " + filePath);
			}
		}
		catch(Exception e) {
			bindingResult.reject(e.getMessage());
			return "edit";
		}	
		productService.saveProduct(model.buildDomain());
		return "result";
	}
	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	public void list(Model model, Locale locale) {
		List<Product> product = productService.getProducts();
		List<ProductModel> productModels = convert(product);
		model.addAttribute("products", productModels);
		model.addAttribute("locale", locale);
	}
	
	@RequestMapping(value="/listjr.do", method=RequestMethod.GET)
	public void listjr(Model model, @RequestParam String format, Locale locale) {
		List<Product> product = productService.getProducts();
		List<ProductModel> productModels = convert(product);
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(productModels);
		model.addAttribute("datasource", source);
		model.addAttribute("format", format);
	}
	
	@RequestMapping(value="/page.do", method=RequestMethod.GET)
	@ModelAttribute("products")
	public List<ProductModel> list(int index, int size) {
		List<Product> product = productService.getProductsByPage(index, size);
		List<ProductModel> productModels = convert(product);
		return productModels;
	}
	
	private List<ProductModel> convert(List<Product> products ) {
		List<ProductModel> productModels = new ArrayList<ProductModel>();
		for(Product product : products) {
			ProductModel productModel = new ProductModel();
			productModel.buildModel(product);
			productModels.add(productModel);
		}
		return productModels;
	}
}
