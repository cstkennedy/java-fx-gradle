package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Main extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@SuppressWarnings("unused")
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			BorderPane root     = new BorderPane();
			GridPane   viewPane = new GridPane();
			Scene      scene    = new Scene(root,400,400);

			Button clickMe = new Button("Click me to write Excel file:");

			clickMe.setOnAction(event ->
			{
				this.writeToExcelFile();
			});

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);

			root.setCenter(clickMe);

			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void writeToExcelFile()
	{
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Report");

		// Output 3 Rows (A, B, C)
		for (int idxRow = 0; idxRow < 3; ++idxRow)
		{
			    Row row = sheet.createRow(idxRow);

	        for (int idxCol = 0; idxCol < 3; ++idxCol)
	        {
	            // Determine the column letter based on the offset from A
	            // (e.g., 'A' + 1 = 'B')
	            final char colLetter = (char) ((int) 'A' + idxCol);
	            // Set up the cell data
	            String cellName =  String.format("%s%d", colLetter, idxRow + 1);

	            // Create the cell and write to it
	            Cell cell = row.createCell(idxCol);
	            cell.setCellValue(cellName);
            }
        }

		try
		{
	        // Write and save the Excel document
	        workbook.write(new FileOutputStream(new File("report.xlsx")));
	        workbook.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
