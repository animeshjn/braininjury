package com.kellar.brain2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class PdfBean {

	public static void generatePDF(Context activity) {
		try {
			String path = Environment.getExternalStorageDirectory().getAbsolutePath()
					+ "/Strategies";
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();
			File file = new File(dir, "Strategies.pdf");
			Document document = new Document(PageSize.A4);
			FileOutputStream fOut = new FileOutputStream(file);
			Log.d("Brain", "before writer");
			PdfWriter.getInstance(document, fOut);
			Log.d("Brain", "before pdf document");
			document.open();
			Log.d("Brain", "before document title");
			document.addTitle("Strategies");
			Log.d("Brain", "add paragraph :");
			//document.add(new Paragraph("Hello World!"));
			
			//getting Image from assets 
			java.io.InputStream ims = activity.getAssets().open("header1.png");
	           Bitmap bmp = BitmapFactory.decodeStream(ims);
	           ByteArrayOutputStream stream = new ByteArrayOutputStream();
	           bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
	           Image image = Image.getInstance(stream.toByteArray());
	           image.scaleToFit(document.getPageSize().getWidth(),500);
	           
	           image.setAbsolutePosition(0, PageSize.A4.getHeight() - image.getScaledHeight());
	          
	           
	           document.add(image);
			
			//document.add(activity.getResources().getDrawable(R.drawable.framefilled));
			Log.d("Brain", "close document");
			document.close();
			
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(file), "application/pdf");
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			activity.startActivity(intent);
		} catch (Exception e) {

			e.printStackTrace();
			Log.d("Brain", "exception " + e.getMessage());
		}
		
		Log.d("Brain", "	START READ ");
		/*READING NOW*/
		File pdfFile = new File( Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/Strategies/Strategies.pdf");
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

	public void setPdfContentString(CheckHandle check)
	{
		boolean strategies[][]=check.getStrategies();
		
		for (int i = 0; i < strategies.length; i++) {
			
			for (int j = 0; j < strategies[i].length; j++) {
				//if i,j is true then 
				if(strategies[i][j])
				{
					//do following
					//00 ->check substrategies too !
					
					
				}
				
				
			}
			
			
		}
			
			
		}
		
		
		
	}
	
	
	
	
