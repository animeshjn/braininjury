package com.kellar.brain2;

import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class PdfBean {

	static ArrayList<Paragraph> plist = new ArrayList<Paragraph>();

	Font headfont;
	ArrayList<PdfPCell> celllist = new ArrayList<PdfPCell>();

	public void initFonts() {
		Font f = new Font();
		f.setColor(BaseColor.RED);
		f.setStyle(Font.UNDERLINE);
		f.setSize(20);
		headfont = f;

	}

	public void generatePDF(Context activity, CheckHandle check) {
		try {

			setPdfContentString(check);
			PdfPTable table = new PdfPTable(1);
			PdfWriter writer = null;
			String path = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/Strategies";

			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();
			File file = new File(dir, "Strategies.pdf");
			Document document = new Document(PageSize.A4);
			table.setWidthPercentage(100);
			
			FileOutputStream fOut = new FileOutputStream(file);
			Log.d("Brain", "before writer");

			writer = PdfWriter.getInstance(document, fOut);
			Log.d("Brain", "before pdf document");
			document.open();
			document.resetPageCount();
			document.top();
			Log.d("Brain", "before document title");
			document.addTitle("Strategies");
			Log.d("Brain", "add paragraph :");
			// document.add(new Paragraph("Hello World!"));
			// getting Image from assets
			java.io.InputStream ims = activity.getAssets().open("header1.png");
			Bitmap bmp = BitmapFactory.decodeStream(ims);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
			Image image = Image.getInstance(stream.toByteArray());
			image.scaleToFit(document.getPageSize().getWidth(), 500);
			image.setAbsolutePosition(0,
					PageSize.A4.getHeight() - image.getScaledHeight());
			PdfPCell imagelogocell = new PdfPCell(image, true);
			table.addCell(imagelogocell);
			for (PdfPCell cell : celllist) {
				table.addCell(cell);
			}
			// document.add(image);
			// document.add(activity.getResources().getDrawable(R.drawable.framefilled));
			document.add(table);
			Log.d("Brain", "close document");
			document.close();
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(file), "application/pdf");
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			writer.close();
			activity.startActivity(intent);

		} catch (Exception e) {
			e.printStackTrace();
			Log.d("Brain", "exception " + e.getMessage());
		}

		Log.d("Brain", "	START READ ");

		/* READING NOW */
		File pdfFile = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/Strategies/Strategies.pdf");
		Log.d("Brain", "OPENED");
		Uri readpath = Uri.fromFile(pdfFile);
		Log.d("Brain", "URI CAPTURED");
		Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
		pdfIntent.setDataAndType(readpath, "application/pdf");
		pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		Log.d("Brain", "STARTING INTENT...");
		activity.startActivity(pdfIntent);
		Log.d("Brain", "STARTED PDF INTENT");
	}

	public void setPdfContentString(CheckHandle check) {
		boolean strategies[][] = check.getStrategies();
		String strategy;
		boolean head = false;
		for (int i = 0; i < strategies.length; i++) {
			head = true;
			for (int j = 0; j < strategies[i].length; j++) {
				// if i,j is true then
				if (strategies[i][j]) {
					/*
					 * This strategy is checkedRetrieve it Print to PDF
					 */
					if (head) {
						Paragraph header = new Paragraph();
						header.add(check.getAreaName(i));
						header.setFont(headfont);
						PdfPCell cell = new PdfPCell();
						cell.addElement(header);
						cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
						cell.setBorder(Rectangle.NO_BORDER);
						celllist.add(cell);
						plist.add(header);
						head = false;
					}
					strategy = check.getStrategy(i, j);
					strategy="\u25CF"+strategy;
					Paragraph p = new Paragraph();
					p.add(strategy);
					plist.add(p);
					PdfPCell subcell = new PdfPCell();
					subcell.addElement(p);
					subcell.setVerticalAlignment(Element.ALIGN_BOTTOM);
					subcell.setBorder(Rectangle.NO_BORDER);
					celllist.add(subcell);
					// If this strategy has sub-strategy
					boolean substrategyChecked[] = check.substrategies;
					ArrayList<Integer> limits = (ArrayList<Integer>) check.subAreaTagMapping
							.get(check.getAreaName(i) + "" + j);
					for (Integer integer : limits) {
						if (substrategyChecked[integer]) {
							CharSequence sub = check.subStrategyMapping
									.get(integer);
							Paragraph subpara = new Paragraph();
							subpara.add(new String("-" + sub));
							subpara.setIndentationLeft(12);
							Log.d("Brain", "SUB :" + sub);
							plist.add(subpara);
							PdfPCell subparacell = new PdfPCell();
							subparacell.addElement(subpara);
							subparacell
									.setVerticalAlignment(Element.ALIGN_BOTTOM);
							subparacell.setBorder(Rectangle.NO_BORDER);
							celllist.add(subparacell);
						}
					}
				}
			}
		}
	}
}
