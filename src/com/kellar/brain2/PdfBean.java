package com.kellar.brain2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfWriter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore.Files;
import android.util.Log;

public class PdfBean implements Runnable {

	static ArrayList<Paragraph> plist = new ArrayList<Paragraph>();
	PdfPTable table = new PdfPTable(1);
	Font headfont;
	ArrayList<PdfPCell> celllist = new ArrayList<PdfPCell>();
	String sid;
	Context context;
	CheckHandle check;
	Document document;

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public CheckHandle getCheck() {
		return check;
	}

	public void setCheck(CheckHandle check) {
		this.check = check;
	}

	public void initFonts() {
		Font f = new Font(FontFamily.HELVETICA, 14, Font.NORMAL, BaseColor.RED);
		headfont = f;

	}

	public void generatePDF(final Context activity, CheckHandle check) {

		try {

			// HAVE A PRINT WRITER HANDY
			PdfWriter writer = null;
			// SETUP PATH
			String path = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/Strategies";
			// TAKEUP DIRECTORY
			File dir = new File(path);

			// MAKE DIRECTORY NATIVE COMMAND
			if (!dir.exists())
				dir.mkdirs();
			File f = new File(dir, "Strategies.pdf");
			f.delete();
			// THE ACTUAL STRATEGIES PDF FILE
			
			File file = new File(dir, "Strategies.pdf");
			//boolean result = java.io.file.Files.deleteIfExists(file.getPath());
			//Rectangle pageSize=new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight()-80);
			
			document = new Document(PageSize.A4);
			table.setWidthPercentage(110);
			FileOutputStream fOut = new FileOutputStream(file);

			// GET WRITER INSTANCE, BIND OUTPUT TO DOCUMENT
			writer = PdfWriter.getInstance(document, fOut);
			writer.setBoxSize("box",PageSize.A4);
			// OPEN THE DOCUMENT
			document.setMargins(50, 50, 50, 120);
			document.open();
			document.resetPageCount();
			document.top();
			// DOCUMENT TITLE
			document.addTitle("Strategies");
			document.setPageSize(PageSize.A4);
			

			
			/* START CREATING FIRST ROW TO BE ADDED TO THE DOC */

			// getting Image from assets
			java.io.InputStream im2 = activity.getAssets().open("header1.png");
			Bitmap bmp2 = BitmapFactory.decodeStream(im2);
			ByteArrayOutputStream stream4 = new ByteArrayOutputStream();
			bmp2.compress(Bitmap.CompressFormat.PNG, 100, stream4);
			Image fimage = Image.getInstance(stream4.toByteArray());
			fimage.scaleToFit(document.getPageSize().getWidth(), 500);
			fimage.setAbsolutePosition(0,
					PageSize.A4.getHeight() - fimage.getScaledHeight());
			PdfPCell fimagelogocell = new PdfPCell(fimage, true);

			// ROW1 ADDED
			table.addCell(fimagelogocell);

			// CREATING THE NEXT ROW
			java.io.InputStream sum = activity.getAssets().open("summary.png");
			Bitmap sumbmp = BitmapFactory.decodeStream(sum);
			ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
			sumbmp.compress(Bitmap.CompressFormat.PNG, 100, stream2);
			Image sumimage = Image.getInstance(stream2.toByteArray());
			sumimage.scaleToFit(document.getPageSize().getWidth(), 500);
			sumimage.setAbsolutePosition(0,
					PageSize.A4.getHeight() - sumimage.getScaledHeight());
			PdfPCell summarycell = new PdfPCell(sumimage, true);

			// ROW2 ADDED
			table.addCell(summarycell);

			/* FOR ADDING STUDENT ID AND DATE */
			SharedPreferences sharedpreferences = activity
					.getSharedPreferences("BrainPreferences",
							Context.MODE_PRIVATE);
			sid = sharedpreferences.getString("student", null);

			Phrase phrase = new Phrase();
			phrase.add(new Chunk("Student ID: " + sid + "              "
					+ "                   " + Calendar.getInstance().getTime(),
					new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD,
							BaseColor.RED)));
			PdfPCell student = new PdfPCell(phrase);
			student.setBorder(0);
			document.top();
			// ROW 3 ADDED
			table.addCell(student);

			// ADD TABLE FIRST
			document.add(table);
			writer.setPageEvent(new PdfPageEvent() {
				@Override
				public void onStartPage(PdfWriter writer, Document document) {
					Rectangle pageSize=new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight()-80);
					document.setPageSize(pageSize);
					document.setMargins(50, 50, 50, 120);
					Log.d("Brain", "start page event"+document.getPageNumber());
					Phrase phrase = new Phrase();
					phrase.add(new Chunk("Student ID: " + sid
							+ "                      " + "   "
							+ Calendar.getInstance().getTime(), new Font(
							Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD,
							BaseColor.RED)));
					PdfPCell student = new PdfPCell(phrase);
					student.setBorder(0);
					PdfPTable headTable = new PdfPTable(1);
					headTable.addCell(student);
//					Rectangle rect =writer.getBoxSize("box");
					writer.getDirectContent();
					try {
						document.top();
						document.add(headTable);
						
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}

				@Override
				public void onChapter(PdfWriter arg0, Document arg1,
						float arg2, Paragraph arg3) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onChapterEnd(PdfWriter arg0, Document arg1,
						float arg2) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onCloseDocument(PdfWriter arg0, Document arg1) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onEndPage(PdfWriter writer, Document doc) {
					// getting Image from assets
					try{
					java.io.InputStream ims = activity.getAssets().open("footerslogo.png");
					Bitmap bmp = BitmapFactory.decodeStream(ims);
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
					Image image = Image.getInstance(stream.toByteArray());
					image.scaleToFit(doc.getPageSize().getWidth(), 300);
					image.scaleAbsoluteHeight(80);
					
					//image.setAbsolutePosition(0,0);
					Rectangle rect=doc.getPageSize();
					image.setAbsolutePosition(0, rect.getBottom());
					PdfPCell cell = new PdfPCell(image);
					PdfPTable foottable = new PdfPTable(1);
					foottable.addCell(cell);
					
					PdfContentByte canvas = writer.getDirectContent();
			//		Chunk c=new Chunk(image,50, rect.getBottom());
		//			Phrase foot= new Phrase(c);
	//				ColumnText.showTextAligned(canvas,Element.ALIGN_CENTER,foot, doc.left(), document.bottom() - 81, 0);
					canvas.addImage(image);
//					doc.bottom();
//					
//					rect=new Rectangle(doc.getPageSize().getWidth(), doc.getPageSize().getHeight()+80);
//					doc.setPageSize(rect);
//					doc.bottom(80);
//					doc.add(foottable);
					
//					
					}catch(Exception e){
							Log.d("Brain",""+e.getMessage());
					}
				}

				@Override
				public void onGenericTag(PdfWriter arg0, Document arg1,
						Rectangle arg2, String arg3) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onOpenDocument(PdfWriter arg0, Document arg1) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onParagraph(PdfWriter arg0, Document arg1,
						float arg2) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onParagraphEnd(PdfWriter arg0, Document arg1,
						float arg2) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSection(PdfWriter arg0, Document arg1,
						float arg2, int arg3, Paragraph arg4) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSectionEnd(PdfWriter arg0, Document arg1,
						float arg2) {
					// TODO Auto-generated method stub

				}
			}

			);

			setPdfContentString(check);

			// Added Whole table at once
			// problem
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
		table.flushContent();

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
						try {
							// Chunk headText = new Chunk(, headfont);
							Paragraph header = new Paragraph();
							header.add(check.getAreaFullName(i));
							header.setFont(headfont);
							PdfPCell cell = new PdfPCell();
							// cell.addElement(header);
							cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
							cell.setBorder(Rectangle.NO_BORDER);
							//PHRASE
							Phrase phrase = new Phrase();
							phrase.add(new Chunk(check.getAreaFullName(i),
									new Font(Font.FontFamily.TIMES_ROMAN, 14,
											Font.BOLD, BaseColor.RED)));
							// phrase.add(new Chunk(", some normal text", new
							// Font()));
							cell.addElement(phrase);
//
//							if (table.getTotalHeight() >=( PageSize.A4.getHeight()-(4*(table.getRowHeight(3)))))
//							{
//								try {
//									PdfPCell latest= table.getRow(table.getLastCompletedRowIndex()).getCells()[0];
//									table.deleteLastRow();
//									document.add(table);
//									table.flushContent();
//									document.newPage();
//									table.addCell(latest);
//									
//								} catch (DocumentException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//								//docuemnt.add(footer_table)
//								
//							}		
							document.add(table);
							table.flushContent();
							table.deleteBodyRows();
							
							table.addCell(cell);
							plist.add(header);
							head = false;
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
					strategy = check.getStrategy(i, j).replace("\n", " ");
					strategy = "\u2022   " + formatString(strategy);
					Chunk p = new Chunk(strategy);

					// plist.add(p);
					PdfPCell subcell = new PdfPCell();
					subcell.addElement(p);
					subcell.setVerticalAlignment(Element.ALIGN_BOTTOM);
					subcell.setBorder(Rectangle.NO_BORDER);
					try {
						document.add(table);
						
						table.flushContent();
						table.deleteBodyRows();
					} catch (DocumentException e1) {
						Log.d("Brain",e1.getMessage());
					}
					
					
					table.addCell(subcell);
					
					
					
//					if (table.getTotalHeight() >=( PageSize.A4.getHeight()-(4*(table.getRowHeight(3)))))
//					{
//						try {
//							PdfPCell latest= table.getRow(table.getLastCompletedRowIndex()).getCells()[0];
//							table.deleteLastRow();
//							document.add(table);
//							table.flushContent();
//							document.newPage();
//							table.addCell(latest);
//							
//						} catch (DocumentException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						//docuemnt.add(footer_table)
//						
//					}
//					
					
	
					// If this strategy has sub-strategy
					boolean substrategyChecked[] = check.substrategies;
					ArrayList<Integer> limits = (ArrayList<Integer>) check.subAreaTagMapping
							.get(check.getAreaName(i) + "" + j);
					for (Integer integer : limits) {
						if (substrategyChecked[integer]) {
							CharSequence sub = check.subStrategyMapping
									.get(integer);
							Paragraph subpara = new Paragraph();
							subpara.add(new String("-" + sub)
									.replace("\n", " "));
							subpara.setIndentationLeft(15);
							Log.d("Brain", "SUB :" + sub);
							plist.add(subpara);
							PdfPCell subparacell = new PdfPCell();
							subparacell.addElement(subpara);
							subparacell
									.setVerticalAlignment(Element.ALIGN_BOTTOM);
							subparacell.setBorder(Rectangle.NO_BORDER);
							try {
								document.add(table);
								table.flushContent();
								table.deleteBodyRows();
							} catch (DocumentException e1) {
								Log.d("Brain",e1.getMessage());
							}
							table.addCell(subparacell);
							
//							if (table.getTotalHeight() >=( PageSize.A4.getHeight()-(4*(table.getRowHeight(3)))))
//							{
//								try {
//									PdfPCell latest= table.getRow(table.getLastCompletedRowIndex()).getCells()[0];
//									table.deleteLastRow();
//									document.add(table);
//									table.flushContent();
//									document.newPage();
//									table.addCell(latest);
//									
//								} catch (DocumentException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//								//docuemnt.add(footer_table)
//								
//							}

							// celllist.add(subparacell);
						}
					}
					
					
				}
			}
			//ADDITIONAL COMMENTS
			if(check.comments[i])
			{
			Paragraph header = new Paragraph();
			header.add(check.getAreaFullName(i));
			header.setFont(headfont);
			PdfPCell cell = new PdfPCell();
			// cell.addElement(header);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setBorder(Rectangle.NO_BORDER);
			//PHRASE
			Phrase phrase = new Phrase();
			phrase.add(new Chunk("    -  Additional Comments",
					new Font(Font.FontFamily.TIMES_ROMAN, 12,
							Font.BOLD, BaseColor.RED)));
			// phrase.add(new Chunk(", some normal text", new
			// Font()));
			cell.addElement(phrase);
			try {
				document.add(table);
			} catch (DocumentException e) {
				
			}
			table.flushContent();
			table.deleteBodyRows();
			table.addCell(cell);
			String comment = check.getCommentText(i);
			comment = "          \u2022   " + formatString(comment);
			Chunk p2 = new Chunk(comment);

			// plist.add(p);
			PdfPCell commentcell = new PdfPCell();
			commentcell.addElement(p2);
			commentcell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			commentcell.setBorder(Rectangle.NO_BORDER);
			table.addCell(commentcell);
			try {
				document.add(table);
				table.flushContent();
				table.deleteBodyRows();
			} catch (DocumentException e1) {
				Log.d("Brain",e1.getMessage());
			}
			
			}
		
		}

	}

	public void addPDFHeader() {
		// TODO add PDF header

		// PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());
		// canvas.beginText().setFontAndSize(
		// PdfFontFactory.createFont(FontConstants.HELVETICA), 12)
		// .moveText(265, 597)
		// .showText("I agree to the terms and conditions.")
		// .endText();

	}

	@Override
	public void run() {
		generatePDF(context, check);

	}

	public String formatString(String string) {
		String words[] = string.split("\\s+");
		String s = "";
		for (int i = 0; i < words.length; i++) {
			s += words[i] + " ";
			if (s.length() + 5 >= PageSize.A4.getWidth())
				s += "\n\t";
		}
		
		return s;
	}

}
