head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.19.05.38.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	72c4dad1fc472db;
filename	WEB3AffinityTestPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : Affinityプラグインクラス(WEB3AffinityPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/09/08 劉 新規作成
 */
package webbroker3.system.tune.affinity;

import java.sql.*;
import java.util.*;
import javax.sql.*;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.data.impl.*;
import com.fitechlabs.xtrade.plugin.util.rac.data.*;

/**
 * Affinityプラグインクラス
 *
 * @@author 劉
 * @@version 1.0
 */
public final class WEB3AffinityTestPlugin
    extends Plugin
{
    /**
     * コンストラクタ。
     */
    public WEB3AffinityTestPlugin()
    {
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        plug(WEB3AffinityTestPlugin.class);
    }

    /**
     * プラグイン処理。
     */
    public static void onPrePlug() throws Exception
    {
        QPFRacImpl qpfracimpl = new QPFRacImpl("rac-config");
        Processors.setProcessorFactory("rac-config", qpfracimpl);
        RacDataDBExtensions.plug();
        RacDataSessionDBExtensions.plug();

    }

    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception
    {
        Services.registerService(WEB3OrderReqNumberHead2ManageService.class,
                                 new WEB3OrderReqNumberHead2ManageTestServiceImpl());

    }

    private static class QPFRacImpl
        extends QPFCachingImpl
    {

        protected DataSource getInitDataSource(Properties properties) throws SQLException
        {
            return DataSources.getDefaultDataSourceServant();
        }

        private void setDataSource(DataSource datasource)
        {
            database.setDataSource(datasource);
        }

        private QPFRacImpl(String s) throws SQLException
        {
            super(null, s);
        }

    }

}
@
