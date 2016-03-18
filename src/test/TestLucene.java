package test;

import lucene.IndexFile;
import lucene.IndexSearch;

import org.junit.Test;

import util.FileUtils;

public class TestLucene {

	@Test
	public void testIndexDocument() throws Exception {
		IndexFile indexFile=new IndexFile();
		String path="G://J2EE/Lucene/test/index";
		indexFile.index(path, "1", FileUtils.readTxt("G://J2EE/Lucene/test/1.txt"),"南京");
		indexFile.index(path, "2", FileUtils.readTxt("G://J2EE/Lucene/test/2.txt"),"上海");
		indexFile.index(path, "3", FileUtils.readTxt("G://J2EE/Lucene/test/3.txt"),"北京");
		
	}	
	
	@Test
	public void testSearchIndex() throws Exception{
		IndexSearch indexSearch=new IndexSearch();
		indexSearch.search("G://J2EE/Lucene/test/index", "content", "下雨");
	}

}
