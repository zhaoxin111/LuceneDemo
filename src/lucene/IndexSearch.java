package lucene;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class IndexSearch {
	Directory directory;//索引存放的位置
	IndexReader indexReader;//索引读对象
	IndexSearcher indexSearcher;//索引搜索对象
	QueryParser queryParser;//搜索词的解析对象
	Query query;//对搜索内容封装的查对象
	/**
	 * 搜索关键字
	 * @param path  索引存放的路径
	 * @param fieldName  字段名
	 * @param queryString  要搜索的关键字
	 * @throws Exception
	 */
	public void search(String path, String fieldName, String queryString)
			throws Exception {
		directory = FSDirectory.open(Paths.get(path));//从硬盘读取
		//directory=new RAMDirectory();//从内存读取
		indexReader = DirectoryReader.open(directory);
		indexSearcher = new IndexSearcher(indexReader);

		Analyzer analyzer = new StandardAnalyzer();
		queryParser = new QueryParser(fieldName, analyzer);
		query = queryParser.parse(queryString);
		TopDocs topDocs = indexSearcher.search(query, 10);//返回搜索的结果集
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;

		System.out.println("一共搜索到" + topDocs.totalHits + "条记录");
		//List<Float> scores = new ArrayList<>();
		for (int i = 0; i < scoreDocs.length; i++) {
			Document document = indexSearcher.doc(scoreDocs[i].doc);
			System.out.println("得分为：" + scoreDocs[i].score + "    id="+document.get("id")+"     content="
					+ document.get("content")+"   city="+document.get("city"));
		}
		indexReader.close();
		directory.close();
		
	}

}
