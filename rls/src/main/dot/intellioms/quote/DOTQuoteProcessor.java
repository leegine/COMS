/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteProcessorクラス(DOTQuoteProcessor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/21 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote;

import com.fitechlabs.fin.intellioms.quote.QuoteProcessor;
import com.fitechlabs.fin.intellioms.util.Startable;


/**
 * (拡張時価プロセッサ)<BR>
 * <BR>
 * 時価プロセッサをWEB3ルールエンジン用に拡張したインターフェース。
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteProcessor extends QuoteProcessor, Startable
{

}
