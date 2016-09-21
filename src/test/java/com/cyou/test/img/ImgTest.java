/**
 * 项目名称：hithere-center2
 * 包名：com.cyou.test.img
 * 文件名：ImgTest.java
 * 版本信息：V1.0
 * 日期：2015-4-25-上午09:15:43
 * 作者：Administrator
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.test.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.resizers.configurations.Antialiasing;

import org.junit.Test;

import com.cyou.test.TestBase;

/**
 * 类名称：ImgTest
 * 类描述：(图片压缩处理)
 * 
 * 创建人：Administrator
 * 创建时间：2015-4-25 上午09:15:43
 * 修改人：
 * 修改时间：2015-4-25 上午09:15:43
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class ImgTest extends TestBase {

	/**
	 * test1(按照大小指定缩放)
	 * 
	 * @throws IOException
	 *             返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	@Test
	public void test1() throws IOException {
		/*
		 * size(width,height) 若图片横比200小，高比300小，不变
		 * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
		 * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
		 */
		Thumbnails.of(new URL("http://pic.159.com/desk/user/2013/5/22/13522104238750.jpg")).size(400, 600)
				.antialiasing(Antialiasing.ON).toFile("D:/image_200x300.jpg");
		// Thumbnails.of("images/test.jpg").size(2560, 2048).toFile("C:/image_2560x2048.jpg");
	}

	/**
	 * test(按照比例指定缩放)
	 * 
	 * @throws Exception
	 *             返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	@Test
	public void test2() throws Exception {
		Thumbnails.of(new URL("http://olpic.tgbusdata.cn/uploads/allimg/140620/166-140620035K4.jpg"))
				.antialiasing(Antialiasing.ON).scale(0.5f).toFile("D://41.jpg");
		Thumbnails.of(new URL("http://pic.159.com/desk/user/2013/5/22/13522104238750.jpg")).antialiasing(Antialiasing.ON)
				.scale(0.5f).toFile("D://51.jpg");
		Thumbnails.of(new URL("http://image.tianjimedia.com/uploadImages/2012/348/A2BT80065IL6.jpg"))
				.antialiasing(Antialiasing.ON).scale(0.5f).toFile("D://61.jpg");
	}

	/**
	 * test3(不按照比例，指定大小进行缩放)
	 * keepAspectRatio(false) 默认是按照比例缩放的
	 * 
	 * @throws IOException
	 *             返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	@Test
	public void test3() throws IOException {
		Thumbnails.of(new URL("http://image.tianjimedia.com/uploadImages/2012/348/A2BT80065IL6.jpg")).size(400, 600)
				.keepAspectRatio(false).toFile("D:/image_120x120.jpg");
	}

	/**
	 * test4(旋转90)
	 * 
	 * @throws IOException
	 *             返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	@Test
	public void test4() throws IOException {
		Thumbnails.of(new URL("http://image.tianjimedia.com/uploadImages/2012/348/A2BT80065IL6.jpg")).size(1280, 1024).rotate(90)
				.keepAspectRatio(false).toFile("D:/image_1280x1024.jpg");
	}

	/**
	 * 水印
	 * 
	 * @throws IOException
	 */
	@Test
	public void test5() throws IOException {
		/**
		 * watermark(位置，水印图，透明度)
		 */
		Thumbnails.of("images/test.jpg").size(1280, 1024)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("images/watermark.png")), 0.5f).outputQuality(0.8f)
				.toFile("C:/image_watermark_bottom_right.jpg");
		Thumbnails.of("images/test.jpg").size(1280, 1024)
				.watermark(Positions.CENTER, ImageIO.read(new File("images/watermark.png")), 0.5f).outputQuality(0.8f)
				.toFile("C:/image_watermark_center.jpg");
	}

	/**
	 * 裁剪
	 * 
	 * @throws IOException
	 */
	public void test6() throws IOException {
		/**
		 * 图片中心400*400的区域
		 */
		Thumbnails.of("images/test.jpg").sourceRegion(Positions.CENTER, 400, 400).size(200, 200).keepAspectRatio(false)
				.toFile("C:/image_region_center.jpg");
		/**
		 * 图片右下400*400的区域
		 */
		Thumbnails.of("images/test.jpg").sourceRegion(Positions.BOTTOM_RIGHT, 400, 400).size(200, 200).keepAspectRatio(false)
				.toFile("C:/image_region_bootom_right.jpg");
		/**
		 * 指定坐标
		 */
		Thumbnails.of("images/test.jpg").sourceRegion(600, 500, 400, 400).size(200, 200).keepAspectRatio(false)
				.toFile("C:/image_region_coord.jpg");
	}

	/**
	 * 转化图像格式
	 * 
	 * @throws IOException
	 */
	public void test7() throws IOException {
		/**
		 * outputFormat(图像格式)
		 */
		Thumbnails.of("images/test.jpg").size(1280, 1024).outputFormat("png").toFile("C:/image_1280x1024.png");
		Thumbnails.of("images/test.jpg").size(1280, 1024).outputFormat("gif").toFile("C:/image_1280x1024.gif");
	}

	/**
	 * 输出到OutputStream
	 * 
	 * @throws IOException
	 */
	public void test8() throws IOException {
		/**
		 * toOutputStream(流对象)
		 */
		OutputStream os = new FileOutputStream("C:/image_1280x1024_OutputStream.png");
		Thumbnails.of("images/test.jpg").size(1280, 1024).toOutputStream(os);
	}

	/**
	 * 输出到BufferedImage
	 * 
	 * @throws IOException
	 */
	public void test9() throws IOException {
		/**
		 * asBufferedImage() 返回BufferedImage
		 */
		BufferedImage thumbnail = Thumbnails.of("images/test.jpg").size(1280, 1024).asBufferedImage();
		ImageIO.write(thumbnail, "jpg", new File("C:/image_1280x1024_BufferedImage.jpg"));
	}
}
