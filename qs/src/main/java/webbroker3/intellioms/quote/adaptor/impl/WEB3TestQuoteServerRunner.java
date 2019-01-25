/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3TestQuoteServerRunnerクラス(WEB3TestQuoteServerRunner.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/09 山田　卓司(FLJ) 新規作成
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

            // 時価サーバを起動する。
            quoteServer = WEB3TestQuoteServer.setUpQuoteServer(l_strArgs);
            quoteServer.start();

            // 処理を開始する。
            process();

        } catch (Throwable l_th)
        {
            System.out
                .println("!!!!! Unexpected exception occured while running. !!!!!");
            l_th.printStackTrace();
        } finally
        {

            // 時価サーバを停止する。
            if (quoteServer != null)
            {
                quoteServer.stop();
            }

        }

    }
    
    private static void process()
    {
        
        // 標準入力からのリーダーを開く。
        BufferedReader l_in = new BufferedReader(new InputStreamReader(System.in));
        String l_strInput = null;

        // ファイル名を入力するようにメッセージを表示
        System.out.println("##### Input file name. #####");

        try
        {
            
            // 標準入力が閉じられるまでループ
            while ((l_strInput = l_in.readLine()) != null)
            {

                // 入力されたファイル名を取得する。
                String l_strFileName = l_strInput.trim();

                // 何も入力されない場合はスキップ
                if (l_strFileName.length() > 0)
                {
                    // 入力されたファイル名から時価情報を読み込む。
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

                // ファイル名を入力するようにメッセージを表示
                System.out.println("##### Input file name. #####");
                
            }

        } catch (IOException l_ioe)
        {
            System.out.println("!!!!! Exception occured in input stream. !!!!!");
            l_ioe.printStackTrace();

        }
    }

}