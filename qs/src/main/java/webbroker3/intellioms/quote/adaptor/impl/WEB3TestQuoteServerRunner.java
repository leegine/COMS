/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3TestQuoteServerRunner�N���X(WEB3TestQuoteServerRunner.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/09 �R�c�@��i(FLJ) �V�K�쐬
 */
package webbroker3.intellioms.quote.adaptor.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class WEB3TestQuoteServerRunner
{

    private static WEB3TestQuoteServer quoteServer;

    public static void main(String[] l_strArgs)
    {
        
        try
        {

            // �����T�[�o���N������B
            quoteServer = WEB3TestQuoteServer.setUpQuoteServer(l_strArgs);
            quoteServer.start();

            // �������J�n����B
            process();

        } catch (Throwable l_th)
        {
            System.out
                .println("!!!!! Unexpected exception occured while running. !!!!!");
            l_th.printStackTrace();
        } finally
        {

            // �����T�[�o���~����B
            if (quoteServer != null)
            {
                quoteServer.stop();
            }

        }

    }
    
    private static void process()
    {
        
        // �W�����͂���̃��[�_�[���J���B
        BufferedReader l_in = new BufferedReader(new InputStreamReader(System.in));
        String l_strInput = null;

        // �t�@�C��������͂���悤�Ƀ��b�Z�[�W��\��
        System.out.println("##### Input file name. #####");

        try
        {
            
            // �W�����͂�������܂Ń��[�v
            while ((l_strInput = l_in.readLine()) != null)
            {

                // ���͂��ꂽ�t�@�C�������擾����B
                String l_strFileName = l_strInput.trim();

                // �������͂���Ȃ��ꍇ�̓X�L�b�v
                if (l_strFileName.length() > 0)
                {
                    // ���͂��ꂽ�t�@�C�������玞������ǂݍ��ށB
                    try
                    {
                        quoteServer.setQuoteData(l_strFileName);
                    } catch (IOException l_ioe)
                    {
                        System.out
                            .println("!!!!! Exception occured while loading quote file.");
                        l_ioe.printStackTrace();
                    }
                }

                // �t�@�C��������͂���悤�Ƀ��b�Z�[�W��\��
                System.out.println("##### Input file name. #####");
                
            }

        } catch (IOException l_ioe)
        {
            System.out.println("!!!!! Exception occured in input stream. !!!!!");
            l_ioe.printStackTrace();

        }
    }

}