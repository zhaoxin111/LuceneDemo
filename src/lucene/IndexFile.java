package lucene;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexFile {
	
	private Directory directory;
	private IndexWriter indexWriter;
	/**
	 *    对文件作索引操作
	 * @param path 索引存放的位置
	 * @param id 字段名
	 * @param content 字段内容
	 * @throws IOException
	 */
	public void index(String path,String id, String content,String city) throws IOException{
		directory=FSDirectory.open(Paths.get(path));//读入硬盘
//		directory=new RAMDirectory();//读入内存
		indexWriter=getWriter(directory);
		Document document=new Document();//document相当于数据库表的一条记录
		document.add(new Field("id", id, TextField.TYPE_STORED));//Fiels就像记录的字段，包括字段名和字段值
		document.add(new TextField("content", content, Store.YES));
		document.add(new Field("city", city, TextField.TYPE_STORED));
		indexWriter.addDocument(document);
		indexWriter.commit();
		indexWriter.close();
		directory.close();
		
	}
	/**
	 * 
	 * @param directory 索引存放的位置
	 * @return 
	 * @throws IOException
	 */
	public IndexWriter getWriter(Directory directory) throws IOException{
		Analyzer analyzer=new StandardAnalyzer();//词法分析器
		IndexWriterConfig config=new IndexWriterConfig(analyzer);//indexwriter配置
		config.setOpenMode(OpenMode.CREATE_OR_APPEND);
		return new IndexWriter(directory, config);
	}
	
}
