head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.17.06.52.10;	author liu-lei;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8d04d81af9a1914;
filename	WEB3RichPushGateWayServiceImpl.java;

1.1
date	2011.03.17.01.32.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushGateWayServiceImpl.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@���b�`�N���C�A���g�v�b�V���Q�[�g�E�F�C�T�[�r�X����(WEB3RichPushGateWayServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 ��(FLJ) �V�K�쐬
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.io.*;
import java.net.*;
import java.util.*;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.rcp.data.*;
import webbroker3.rcp.service.delegate.*;
import webbroker3.util.*;

/**
 * ���b�`�N���C�A���g�v�b�V���Q�[�g�E�F�C�T�[�r�X����
 * @@author ��
 * @@version 1.0
 */
public class WEB3RichPushGateWayServiceImpl
    implements WEB3RichPushGateWayService
{

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushGateWayServiceImpl.class);

    /** HTTP_OK_CODE */
    private static final String HTTP_OK_CODE =
        "200";

    /** ���b�`�N���C�A���g�v�b�V���ڑ���URL�ݒ�L�[ */
    private final static String STR_RICH_PUSH_URL_KEY =
        "rich.push.url.";

    private final static String STR_RICH_PUSH_PROXY_SET_KEY = "rich.push.proxy.set";

    private final static String STR_RICH_PUSH_PROXY_HOST_KEY = "rich.push.proxy.host";

    private final static String STR_RICH_PUSH_PROXY_PORT_KEY = "rich.push.proxy.port";

    /**
     * �Q�[�g�E�F�C�o�R���b�`�N���C�A���g�փf�[�^�v�b�V��
     *
     * @@param l_strInstitutionCode String
     * @@param l_lstPushData List
     * @@return boolean
     */
    public boolean push(String l_strInstitutionCode, List l_lstPushData)
    {
        StringBuffer l_strbuff = new StringBuffer();
        l_strbuff.append(WEB3RichPushObjectToXMLConverter.XML_HEAD);
        l_strbuff.append(WEB3RichPushObjectToXMLConverter.XML_MAIN_TAG_NAME_S);

        ArrayList l_lstTmp = new ArrayList();
        for (int i = 0; i < l_lstPushData.size(); i++)
        {
            l_lstTmp.clear();
            Row l_row = (Row) l_lstPushData.get(i);
            l_lstTmp.add(l_row);
            if (l_row instanceof RichPushEquityOrderAcceptRow)
            {
                WEB3RichPushEquityMarginOrderAcceptUnitService l_service =
                    (WEB3RichPushEquityMarginOrderAcceptUnitService) Services.getService(
                    WEB3RichPushEquityMarginOrderAcceptUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushSwapOrderAcceptRow)
            {
                WEB3RichPushSwapOrderAcceptUnitService l_service =
                    (WEB3RichPushSwapOrderAcceptUnitService) Services.getService(
                    WEB3RichPushSwapOrderAcceptUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushEquityChangeCancelRow)
            {
                WEB3RichPushEquityMarginChangeCancelUnitService l_service =
                    (WEB3RichPushEquityMarginChangeCancelUnitService) Services.getService(
                    WEB3RichPushEquityMarginChangeCancelUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushEquityContRow)
            {
                WEB3RichPushEquityMarginContUnitService l_service =
                    (WEB3RichPushEquityMarginContUnitService) Services.getService(
                    WEB3RichPushEquityMarginContUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushEquityLapseRow)
            {
                WEB3RichPushEquityMarginLapseUnitService l_service =
                    (WEB3RichPushEquityMarginLapseUnitService) Services.getService(
                    WEB3RichPushEquityMarginLapseUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushIfoOrderAcceptRow)
            {
                WEB3RichPushFuOpOrderAcceptUnitService l_service =
                    (WEB3RichPushFuOpOrderAcceptUnitService) Services.getService(
                    WEB3RichPushFuOpOrderAcceptUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushIfoChangeCancelRow)
            {

                WEB3RichPushFuOpChangeCancelUnitService l_service =
                    (WEB3RichPushFuOpChangeCancelUnitService) Services.getService(
                    WEB3RichPushFuOpChangeCancelUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushIfoContRow)
            {
                WEB3RichPushFuOpContUnitService l_service =
                    (WEB3RichPushFuOpContUnitService) Services.getService(
                    WEB3RichPushFuOpContUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushIfoLapseRow)
            {
                WEB3RichPushFuOpLapseUnitService l_service =
                    (WEB3RichPushFuOpLapseUnitService) Services.getService(
                    WEB3RichPushFuOpLapseUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }

        }
        l_strbuff.append(WEB3RichPushObjectToXMLConverter.XML_MAIN_TAG_NAME_E);
        boolean l_ret = push(l_strInstitutionCode, l_strbuff.toString());
        return l_ret;
    }

    /**
     * �Q�[�g�E�F�C�o�R���b�`�N���C�A���g�փf�[�^�v�b�V��
     *
     * @@param l_strInstitutionCode String
     * @@param l_strXmlData String
     * @@return boolean
     */
    private boolean push(String l_strInstitutionCode, String l_strXmlData)
    {

        final String STR_METHOD_NAME = "push()";
        log.entering(STR_METHOD_NAME);

        log.debug("rich client push data >>>" + l_strInstitutionCode + ":" + l_strXmlData);
        String l_strPushUrl = getPushUrl(l_strInstitutionCode);

        if (l_strPushUrl == null)
        {
            log.error("�v�b�V��URL������ł��Ȃ����߁A���M���s�I");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //PROXY �Ή�
        String l_strPushProxySet = GtlUtils.getTradingSystem().getPreference(
            STR_RICH_PUSH_PROXY_SET_KEY);
        log.debug("rich client push proxy set >>>" + l_strPushProxySet);
        if ("true".equals(l_strPushProxySet))
        {
            String l_strPushProxyHost = GtlUtils.getTradingSystem().getPreference(
                STR_RICH_PUSH_PROXY_HOST_KEY);
            log.debug("rich client push proxy host >>>" + l_strPushProxyHost);
            String l_strPushProxyPort = GtlUtils.getTradingSystem().getPreference(
                STR_RICH_PUSH_PROXY_PORT_KEY);
            log.debug("rich client push proxy port >>>" + l_strPushProxyPort);
            if (l_strPushProxyHost == null || l_strPushProxyPort == null)
            {
                log.error("�v�b�V��PORXY�T�[�o������ł��Ȃ����߁A���M���s�I");
                log.error("rich client push proxy host >>>" + l_strPushProxyHost);
                log.error("rich client push proxy port >>>" + l_strPushProxyPort);
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            Properties sys = System.getProperties();
            sys.put("http.proxyHost", l_strPushProxyHost);
            sys.put("http.proxyPort", l_strPushProxyPort);
        }

        try
        {
            // URL�N���X�̃C���X�^���X�𐶐�
            URL l_postURL =
                new URL(l_strPushUrl);

            // �ڑ����܂�
            URLConnection con = l_postURL.openConnection();
            // �o�͂��s���悤�ɐݒ肵�܂�
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "text/XML;charset=utf-8");

            // �o�̓X�g���[�����擾
            PrintWriter out = new PrintWriter(con.getOutputStream());
            // �o�̓X�g���[����xml��������
            out.print(l_strXmlData);
            out.close();

            // ���M���ʂ𔻒�
            String l_response_code = con.getHeaderField(0);
            log.debug("response code:" + l_response_code);
            if (l_response_code != null && l_response_code.indexOf(HTTP_OK_CODE) >= 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }

        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * getPushUrl
     *
     * @@param String l_strInstitutionCode String
     * @@return String
     */
    private String getPushUrl(String l_strInstitutionCode)
    {

        final String STR_METHOD_NAME = "getPushUrl()";
        log.entering(STR_METHOD_NAME);

        log.debug("�v�b�V��URL����肷��L�[=" + STR_RICH_PUSH_URL_KEY + l_strInstitutionCode);
        String l_strPushUrl = GtlUtils.getTradingSystem().getPreference(
            STR_RICH_PUSH_URL_KEY + l_strInstitutionCode);

        if (l_strPushUrl == null)
        {
            log.error("�v�b�V��URL�����݂��܂���I");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.debug("rich.push.url=" + l_strPushUrl);
        log.exiting(STR_METHOD_NAME);
        return l_strPushUrl;

    }

}
@


1.1
log
@*** empty log message ***
@
text
@a19 1
//import weblogic.net.http.HttpClient;
d203 2
a204 16
            sys.put("proxySet", "true");
            sys.put("proxyHost", l_strPushProxyHost);
            sys.put("proxyPort", l_strPushProxyPort);
            System.setProperties(sys);

            log.debug("���ݒ�O��");
            log.debug("proxyHost: " + HttpClient.proxyHost);
            log.debug("proxyPortt: " + HttpClient.proxyPort);

            //proxy�̍Đݒ�
            HttpClient.resetProperties();

            log.debug("���ݒ�い");
            log.debug("proxyHost: " + HttpClient.proxyHost);
            log.debug("proxyPortt: " + HttpClient.proxyPort);

@

