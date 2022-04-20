package com.ad.common.tools;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author Chenyh
 * @create 2021/6/3 17:00
 **/
public class LocalFileUtils {

    public static void main(String[] args) throws Exception {
        String fileName = "20210604103529.zip";
//        String localZipContent = getLocalZipContent(fileName);
//        System.out.println(localZipContent);
//        boolean delete = deleteLocalZip(fileName);
//        System.out.println(delete);
        String path = LocalFileUtils.getResourceBasePath() + "/temp/" + fileName;
        //解压压缩文件
        unZipFiles(path);
        //删除压缩文件
        deleteLocalZip(fileName);
        //获取文件内容
        String localJsonContent = getLocalJsonContent(fileName.split("\\.")[0]);
        System.out.println(localJsonContent);
        //deleteLocalJson
        deleteLocalJson(fileName.split("\\.")[0]);

    }

    public List<String> getLocalZipList() {
        String path = LocalFileUtils.getResourceBasePath() + "/temp/";
        File[] fileList = new File(path).listFiles();
        List<String> target = new ArrayList<>();
        for (File file : fileList) {
            if (file.exists() && file.isFile()) {
                target.add(file.getName());
            }
        }
        return target;
    }


    /**
     * 本地压缩文件读取内容
     *
     * @param fileName
     * @return
     */
    public static String getLocalZipJson(String fileName) {
        String path = LocalFileUtils.getResourceBasePath() + "/temp/" + fileName;
        String localJsonContent = "";
        //解压压缩文件
        boolean b = unZipFiles(path);
        //删除压缩文件
        deleteLocalZip(fileName);
        if (b) {
            //获取文件内容
            localJsonContent = getLocalJsonContent(fileName.split("\\.")[0]);
            //删除本地文件
            deleteLocalJson(fileName.split("\\.")[0]);
        } else {
            System.out.println(fileName);
        }
        return localJsonContent;
    }


    /**
     * 解压指定路径文件
     *
     * @param path
     * @throws Exception
     */
    public static boolean unZipFiles(String path) {
        //解压目的文件夹
        String descDir = LocalFileUtils.getResourceBasePath() + "/temp/";
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = null;
        try {
            zip = new ZipFile(new File(path));
        } catch (IOException e) {
            return false;
        }
        try {
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);
                String outPath = (descDir + "/" + zipEntryName).replaceAll("\\*", "/");
                // 判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 删除本地zip文件
     *
     * @param fileName
     * @return
     */
    public static boolean deleteLocalZip(String fileName) {
        String path = LocalFileUtils.getResourceBasePath() + "/temp/" + fileName;
        File file = new File(path);
        boolean success = false;
        if (file.exists()) {
            success = file.delete();
        }
        return success;
    }

    /**
     * 删除本地Json文件
     *
     * @param fileName
     * @return
     */
    public static boolean deleteLocalJson(String fileName) {
        String zipPath = "data/kafka/";
        String path = LocalFileUtils.getResourceBasePath() + "/temp/" + zipPath + fileName + ".json";
        File file = new File(path);
        boolean success = false;
        if (file.exists()) {
            success = file.delete();
        }
        return success;
    }

    /**
     * 获取本地JSON文件内容
     *
     * @param fileName
     * @return
     */
    public static String getLocalJsonContent(String fileName) {
        StringBuffer resultBuffer = new StringBuffer();
        String encoding = "UTF-8";
        String zipPath = "data/kafka/";
        String path = LocalFileUtils.getResourceBasePath() + "/temp/" + zipPath + fileName + ".json";
        File file = new File(path);
        try {
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(Files.newInputStream(file.toPath()), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    resultBuffer.append(line + "\n");
                }
                read.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBuffer.toString();
    }

    /**
     * 本地压缩文件读取内容
     *
     * @param fileName
     * @return
     */
    public static String getLocalZipContent(String fileName) {
        String path = LocalFileUtils.getResourceBasePath() + "/temp/" + fileName;
        return getZipContent(path);
    }

    /**
     * 获取压缩文件中json文件的内容
     *
     * @param path 压缩文件路径
     * @return
     * @throws IOException
     */
    public static String getZipContent(String path) {
        StringBuilder resultBuffer = new StringBuilder();
        ZipInputStream zin = null;
        ZipFile zf = null;
        try {
            zf = new ZipFile(path);
            InputStream in = new BufferedInputStream(Files.newInputStream(Paths.get(path)));
            zin = new ZipInputStream(in, StandardCharsets.UTF_8);
            ZipEntry ze;
            if ((ze = zin.getNextEntry()) != null) {
                if (ze.toString().endsWith("json")) {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(zin))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            resultBuffer.append(line).append("\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                zf.close();
                zin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultBuffer.toString();
    }


    /**
     * 获取项目根路径
     *
     * @return
     */
    public static String getResourceBasePath() {
        // 获取跟目录
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            // nothing to do
        }
        if (path == null || !path.exists()) {
            path = new File("");
        }
        String pathStr = path.getAbsolutePath();
        // 如果是在IDE中运行，则和target同级目录,如果是jar部署到服务器，则默认和jar包同级
        pathStr = pathStr.replace("\\target\\classes", "");
        return pathStr;
    }


    /**
     * 不解压直接读取ZIP压缩文件内容
     *
     * @param zipFile
     * @return
     */
    public static String readZipContent(File zipFile) {
        try {
            ZipFile zf = new ZipFile(zipFile);
            InputStream in = new BufferedInputStream(new FileInputStream(zipFile));
            ZipInputStream zin = new ZipInputStream(in);
            ZipEntry ze;
            StringBuilder stringBuilder = new StringBuilder();
            while ((ze = zin.getNextEntry()) != null) {
                if (ze.isDirectory()) {
                } else {
                    long size = ze.getSize();
                    if (size > 0) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
                        String line;
                        while ((line = br.readLine()) != null) {
                            stringBuilder.append(line);
                            stringBuilder.append(System.lineSeparator());
                        }
                        br.close();
                    }
                }
            }
            zin.closeEntry();
            in.close();
            zf.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }

}
