package com.ad.common.utils;

import cn.hutool.core.io.file.FileNameUtil;
import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author CoderYoung
 * <pre>
 *     解压缩
 * </pre>
 */
public class CompressUtil {

    private static final String RAR = "rar";

    public static void unCompress(String oriFile) throws Exception {
        unCompress(new File(oriFile));
    }

    public static void unCompress(File oriFile) throws Exception {
        unCompress(oriFile, oriFile.getParentFile(), FileNameUtil.extName(oriFile));
    }

    public static void unCompress(File oriFile, File dstDir) throws Exception {
        unCompress(oriFile, dstDir, FileNameUtil.extName(oriFile));
    }

    public static void unCompress(File oriFile, Path dstDir) throws Exception {
        unCompress(oriFile, dstDir.toFile(), FileNameUtil.extName(oriFile));
    }

    /**
     * 解压缩
     *
     * @param oriFile      原始压缩文件
     * @param dstDir       解压目录
     * @param compressType 压缩类型
     */
    public static void unCompress(File oriFile, File dstDir, String compressType) throws Exception {
        if (oriFile.exists() && oriFile.isFile()) {
            if (StringUtils.isBlank(compressType)) {
                compressType = FileNameUtil.extName(oriFile);
            }
            if (!dstDir.exists()) {
                dstDir.mkdirs();
            }
            if (ArchiveStreamFactory.SEVEN_Z.equalsIgnoreCase(compressType)) {
                unCompress7z(oriFile, dstDir);
            } else if (RAR.equalsIgnoreCase(compressType)) {
                unCompressRar(oriFile, dstDir);
            } else {
                final InputStream inputStream = Files.newInputStream(oriFile.toPath());
                ArchiveInputStream archiveInputStream = new ArchiveStreamFactory().createArchiveInputStream(compressType, inputStream);
                ArchiveEntry archiveEntry;
                while ((archiveEntry = archiveInputStream.getNextEntry()) != null) {
                    if (archiveEntry.isDirectory()) {
                        dstDir.toPath().resolve(archiveEntry.getName()).toFile().mkdirs();
                    } else {
                        OutputStream outputStream = Files.newOutputStream(dstDir.toPath().resolve(archiveEntry.getName()));
                        IOUtils.copy(archiveInputStream, outputStream);
                        outputStream.close();
                    }
                }
                archiveInputStream.close();
                inputStream.close();
            }
        } else {
            throw new FileNotFoundException();
        }
    }

    private static void unCompress7z(File oriFile, File dstDir) throws IOException {
        SevenZFile zFile = new SevenZFile(oriFile);
        SevenZArchiveEntry entry;
        while ((entry = zFile.getNextEntry()) != null) {
            if (entry.isDirectory()) {
                dstDir.toPath().resolve(entry.getName()).toFile().mkdirs();
            } else {
                OutputStream outputStream = Files.newOutputStream(dstDir.toPath().resolve(entry.getName()));
                BufferedOutputStream bos = new BufferedOutputStream(outputStream);
                int len = -1;
                byte[] buf = new byte[1024];
                while ((len = zFile.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                // 关流顺序，先打开的后关闭
                bos.close();
                outputStream.close();
            }
        }
    }

    private static void unCompressRar(File oriFile, File dstDir) throws IOException, RarException {
        Archive archive = new Archive(new FileInputStream(oriFile));
        archive.getMainHeader().print();
        FileHeader fileHeader = archive.nextFileHeader();
        while (fileHeader != null) {
            String fileName = fileHeader.getFileNameW().isEmpty() ? fileHeader.getFileNameString() : fileHeader.getFileNameW();
            if (fileHeader.isDirectory()) {
                dstDir.toPath().resolve(fileName).toFile().mkdirs();
            } else {
                String[] fileParts = fileName.split("\\\\");
                StringBuilder filePath = new StringBuilder();
                for (String filePart : fileParts) {
                    filePath.append(filePart).append(File.separator);
                }
                fileName = filePath.substring(0, filePath.length() - 1);
                File out = dstDir.toPath().resolve(fileName).toFile();
                if (!out.getParentFile().exists()) {
                    out.getParentFile().mkdirs();
                }
                FileOutputStream os = new FileOutputStream(out);
                archive.extractFile(fileHeader, os);

                os.close();
            }
            fileHeader = archive.nextFileHeader();
        }
        archive.close();
    }

}
