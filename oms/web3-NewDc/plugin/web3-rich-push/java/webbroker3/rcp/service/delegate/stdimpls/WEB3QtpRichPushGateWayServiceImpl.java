head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.24.01.43.01;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5584d8aa1a54f65;
filename	WEB3QtpRichPushGateWayServiceImpl.java;

1.1
date	2011.03.17.01.32.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRichPushGateWayServiceImpl.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@QTP���b�`�N���C�A���g�v�b�V���Q�[�g�E�F�C�T�[�r�X����(WEB3QtpRichPushGateWayServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/24 ��(FLJ) �V�K�쐬
                  : 2009/06/03 ��(FTL) ���Ή�
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.rcp.data.qtp.QtpRichPushEqChangecancelRow;
import webbroker3.rcp.data.qtp.QtpRichPushEqOrderacceptRow;
import webbroker3.rcp.data.qtp.QtpRichPushEquityContRow;
import webbroker3.rcp.data.qtp.QtpRichPushEquityLapseRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoChangecancelRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoContRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoLapseRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoOrderacceptRow;
import webbroker3.rcp.data.qtp.QtpRichPushSwOrderacceptRow;
import webbroker3.rcp.define.WEB3QtpRichPushTextDef;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginContUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpContUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushGateWayService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushSwapOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * QTP���b�`�N���C�A���g�v�b�V���Q�[�g�E�F�C�T�[�r�X����
 * 
 * @@author ��
 * @@version 1.0
 */
public class WEB3QtpRichPushGateWayServiceImpl implements WEB3QtpRichPushGateWayService
{

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3QtpRichPushGateWayServiceImpl.class);

    /** HTTP_OK_CODE */
    private static final String HTTP_OK_CODE = "200";

    private static final String ENCODE = "UTF-8";
    
    private static final String DEFAULT_TIMEOUT = "60000";

    /** ���b�`�N���C�A���g�v�b�V���ڑ���URL�ݒ�L�[ */
    private final static String STR_RICH_PUSH_URL_KEY = "qtp.rich.push.url.";

    /** �،���ЋL�q�q */
    private final static String STR_INSTITUTION_DESC = "qtp.rich.push.institution.desc.";

    private final static String STR_RICH_PUSH_MAX_LENGTH = "qtp.rich.push.max.length";

    private final static int DEFAULT_MAX_LENGTH = 4096;

    private final static String STR_RICH_PUSH_PROXY_SET_KEY = "qtp.rich.push.proxy.set";

    private final static String STR_RICH_PUSH_PROXY_HOST_KEY = "qtp.rich.push.proxy.host";

    private final static String STR_RICH_PUSH_PROXY_PORT_KEY = "qtp.rich.push.proxy.port";
    
    private final static String STR_RICH_PUSH_AUTH_USER_NAME = "qtp.rich.push.auth.user.name.";

    private final static String STR_RICH_PUSH_AUTH_USER_PASS = "qtp.rich.push.auth.user.pass.";
    
    private final static String STR_RICH_PUSH_CONN_TIMEOUT = "qtp.rich.push.conn.timeout";

    private final static String STR_RICH_PUSH_READ_TIMEOUT = "qtp.rich.push.read.timeout";

    /** �v�b�V���T�[�r�X */
    protected final static HashMap pushServices = new HashMap();

    public WEB3QtpRichPushGateWayServiceImpl()
    {
        synchronized(pushServices)
        {
            if(pushServices.size() == 0)
            {
                // �����M�p������t�ʒm
                pushServices.put(QtpRichPushEqOrderacceptRow.TYPE,
                    Services.getService(WEB3QtpRichPushEquityMarginOrderAcceptUnitService.class));
                // �����M�p��������ʒm
                pushServices.put(QtpRichPushEqChangecancelRow.TYPE,
                    Services.getService(WEB3QtpRichPushEquityMarginChangeCancelUnitService.class));
                // �����M�p�o���ʒm
                pushServices.put(QtpRichPushEquityContRow.TYPE,
                    Services.getService(WEB3QtpRichPushEquityMarginContUnitService.class));
                // �����M�p�����ʒm
                pushServices.put(QtpRichPushEquityLapseRow.TYPE,
                    Services.getService(WEB3QtpRichPushEquityMarginLapseUnitService.class));
                // �M�p�������n������t�ʒm
                pushServices.put(QtpRichPushSwOrderacceptRow.TYPE,
                    Services.getService(WEB3QtpRichPushSwapOrderAcceptUnitService.class));
                // �敨�n�o������t�ʒm
                pushServices.put(QtpRichPushIfoOrderacceptRow.TYPE,
                    Services.getService(WEB3QtpRichPushFuOpOrderAcceptUnitService.class));
                // �敨�n�o��������ʒm
                pushServices.put(QtpRichPushIfoChangecancelRow.TYPE,
                    Services.getService(WEB3QtpRichPushFuOpChangeCancelUnitService.class));
                // �敨�n�o�o���ʒm
                pushServices.put(QtpRichPushIfoContRow.TYPE,
                    Services.getService(WEB3QtpRichPushFuOpContUnitService.class));
                // �敨�n�o�����ʒm
                pushServices.put(QtpRichPushIfoLapseRow.TYPE,
                    Services.getService(WEB3QtpRichPushFuOpLapseUnitService.class));

                // �T�[�r�X�o���邱�Ƃ̃`�F�b�N
                Iterator l_iterator = pushServices.entrySet().iterator();
                while(l_iterator.hasNext())
                {
                    Entry l_entry = (Entry)l_iterator.next();
                    if(l_entry.getValue() == null)
                    {
                        throw new NullPointerException("Push service <" + l_entry.getKey() + "> is null!!");
                    }
                }
            }
        }
    }

    /**
     * �Q�[�g�E�F�C�o�R���b�`�N���C�A���g�փf�[�^�v�b�V��
     * 
     * @@param l_strInstitutionCode
     *            String
     * @@param l_lstPushData
     *            List
     * @@return boolean
     */
    public boolean[] push(String l_strInstitutionCode, List l_lstPushData)
    {
        final String STR_METHOD_NAME = "push(String, List)";
        log.entering(STR_METHOD_NAME);

        if (l_lstPushData == null)
        {
            log.error("�v�b�V���f�[�^���Ȃ��A���M���s�I");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�،���ЋL�q�q
        String l_institution = GtlUtils.getTradingSystem().getPreference(STR_INSTITUTION_DESC + l_strInstitutionCode);

        if (l_institution == null || "".equals(l_institution))
        {
            log.error("�،���ЋL�q�q���擾�ł��Ȃ��IPreference��ǉ����������B�L�[�F" + STR_INSTITUTION_DESC + l_strInstitutionCode);
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //xml�w�b�h�́i�o�C�g�Łj����
        int l_headLength = getByteLength(WEB3QtpRichPushObjectToXMLConverter.XML_HEAD + WEB3QtpRichPushObjectToXMLConverter.XML_DESC_HEAD
                + l_institution + WEB3QtpRichPushObjectToXMLConverter.XML_DESC_TAIL + WEB3QtpRichPushObjectToXMLConverter.XML_TAIL);

        //xml���b�Z�[�W�̍ő咷�����擾�A�擾�ł��Ȃ��ꍇ�A�f�t�H�[���g�̒�����ݒ�
        int l_maxLength = 0;
        try
        {
            l_maxLength = Integer.parseInt(GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_MAX_LENGTH));
        }
        catch (Exception e)
        {
            log.warn("Get max length from system preference error, will use default max length.", e);
        }
        if (l_maxLength == 0)
        {
            l_maxLength = DEFAULT_MAX_LENGTH;
        }

        //�v�b�V���f�[�^�̔z����쐬
        WEB3QtpExcutionInformUnit[] l_data = new WEB3QtpExcutionInformUnit[l_lstPushData.size()];
        //���X�|���X���ʃ}�b�v
        Map l_allMap = new HashMap();
        //xml������ۑ��p�o�b�t�@@
        StringBuffer l_sb = new StringBuffer(l_maxLength);
        for (int i = 0; i < l_lstPushData.size(); i++)
        {
            Row l_row = (Row) l_lstPushData.get(i);
            //Row�f�[�^�I�u�W�F�N�g���擾�A�f�[�^�^�C�v�ɂ��A������IFO�̖��ʒm�v�b�V���f�[�^���쐬����
            WEB3QtpRichPushUnitService l_service = (WEB3QtpRichPushUnitService)pushServices.get(l_row.getRowType()); 
            // �T�[�r�X���o�^�̏ꍇ�A����Row�f�[�^�֍s��
            if(l_service == null)
            {
                log.error("unknown data, qtp push skipped: " + l_row.getRowType());
                continue;
            }

            // �擾�����T�[�r�X���@@WEB3QtpExcutionInformUnit�𐶐�����
            l_data[i] = l_service.createRichPushXmlMessage(l_row);

            //��̃v�b�V���f�[�^��xml���e���쐬
            String l_unitXml = WEB3QtpRichPushObjectToXMLConverter.toXML(l_data[i]);
            //�v�b�V��xml�̍��v�̒����͍ő咷���𒴂���ꍇ�A�O��܂ł�xml���e�𑗐M����
            if ((getByteLength(l_sb.toString()) + getByteLength(l_unitXml) + l_headLength) > l_maxLength)
            {
                //���M����
                String l_response = push(l_strInstitutionCode, getFullXmlString(l_institution, l_sb.toString()));
                //���M���ʂ����
                Map l_map = WEB3QtpRichPushObjectToXMLConverter.parseResult(l_response);
                //�S�̂̌��ʃ}�b�v�ɒǉ�
                l_allMap.putAll(l_map);
                //�o�b�t�@@���N���A
                l_sb.setLength(0);
            }
            //�����xml����ǉ�
            l_sb.append(l_unitXml);

        }
        //���[�v����������A�c����e������΁A���M����
        if (l_sb.length() > 0)
        {
            String l_response = push(l_strInstitutionCode, getFullXmlString(l_institution, l_sb.toString()));
            Map l_map = WEB3QtpRichPushObjectToXMLConverter.parseResult(l_response);
            l_allMap.putAll(l_map);
        }
        //���s���ʕۑ��p�z��
        boolean[] l_ret = new boolean[l_data.length];
        ////�v�b�V���f�[�^�̌����������[�v�A�Y�����郌�X�|���X���ʂ��擾�B
        for (int i = 0; i < l_data.length; i++)
        {
            String l_result = (String) l_allMap.get(l_data[i]);
            //���ʂ�null�łȂ�����"SUCCESS"�̏ꍇ�A����I���Ƃ���B����ȊO�A�����G���[
            if (l_result != null)
            {
                if (WEB3QtpRichPushTextDef.RESPONSE_SUCCESS.equals(l_result.toUpperCase()))
                {
                    l_ret[i] = true;
                }
                else
                {
                    log.warn("Result is :" + l_result + ". For the data:" + l_data[i]);
                    l_ret[i] = false;
                }
            }
            else
            {
                l_ret[i] = false;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_ret;
    }

    /**
     * �Q�[�g�E�F�C�o�R���b�`�N���C�A���g�փf�[�^�v�b�V��
     * 
     * Url�Ȃ��̏ꍇ�AProxy�����ł��Ȃ��ꍇ�AHTTP�ڑ��G���[�AHTTP���X�|���X���200�ȊO�̏ꍇ�Anull��Ԃ�
     * 
     * 
     * @@param l_strInstitutionCode
     *            String
     * @@param l_strXmlData
     *            String
     * @@return boolean
     */
    private String push(String l_strInstitutionCode, String l_xmlString)
    {

        final String STR_METHOD_NAME = "push(String,String)";
        log.entering(STR_METHOD_NAME);

        String l_result = null;
        //�،���ЃR�[�h���瑗�MUrl���擾
        String l_strPushUrl = getPushUrl(l_strInstitutionCode);
        //Url�Ȃ��̏ꍇ�A����null��Ԃ�
        if (l_strPushUrl == null)
        {
            log.error("�v�b�V��URL������ł��Ȃ����߁A���M���s�I");
            log.exiting(STR_METHOD_NAME);

            return l_result;
        }

        //PROXY �Ή�
        String l_strPushProxySet = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_PROXY_SET_KEY);
        log.debug("rich client push proxy set >>>" + l_strPushProxySet);
        String l_strPushProxyHost = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_PROXY_HOST_KEY);
        log.debug("rich client push proxy host >>>" + l_strPushProxyHost);
        String l_strPushProxyPort = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_PROXY_PORT_KEY);
        log.debug("rich client push proxy port >>>" + l_strPushProxyPort);
        String l_userName = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_AUTH_USER_NAME+l_strInstitutionCode);
        log.debug("rich client push authorrization username >>>" + l_userName);
        String l_password = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_AUTH_USER_PASS+l_strInstitutionCode);
        log.debug("rich client push authorrization password >>>" + l_password);
        
        String l_cTimeout = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_CONN_TIMEOUT);
        if(l_cTimeout == null)
        {
            l_cTimeout = DEFAULT_TIMEOUT;
        }
        log.debug("rich client connnect timeout >>>" + l_cTimeout);
        String l_rTimeout = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_READ_TIMEOUT);
        if(l_rTimeout == null)
        {
            l_rTimeout = DEFAULT_TIMEOUT;
        }
        log.debug("rich client read timeout >>>" + l_rTimeout);        
        
        synchronized (this)
        {
            if ("true".equals(l_strPushProxySet))
            {
                if (l_strPushProxyHost == null || l_strPushProxyPort == null)
                {
                    log.error("�v�b�V��PORXY�T�[�o������ł��Ȃ����߁A���M���s�I");
                    log.error("rich client push proxy host >>>" + l_strPushProxyHost);
                    log.error("rich client push proxy port >>>" + l_strPushProxyPort);
                    log.exiting(STR_METHOD_NAME);
                    return l_result;
                }

                Properties sys = System.getProperties();
                sys.put("https.proxySet", "true");
                sys.put("https.proxyHost", l_strPushProxyHost);
                sys.put("https.proxyPort", l_strPushProxyPort);
                sys.put("proxySet", "true");
                sys.put("proxyHost", l_strPushProxyHost);
                sys.put("proxyPort", l_strPushProxyPort);
                System.setProperties(sys);

            }
            else
            {
                Properties sys = System.getProperties();
                sys.put("https.proxySet", "false");
                sys.put("https.proxyHost", "");
                sys.put("https.proxyPort", "");
                sys.put("proxySet", "false");
                sys.put("proxyHost", "");
                sys.put("proxyPort", "");

            }
            
            Properties sys = System.getProperties();
            sys.put("sun.net.client.defaultConnectTimeout", l_cTimeout);
            sys.put("sun.net.client.defaultReadTimeout", l_rTimeout);
            
            HttpURLConnection con = null;
            //PrintWriter l_out = null;
            OutputStream l_out = null;
            BufferedReader l_is = null;
            try
            {
                if (log.ison())
                {
                    log.debug("Send Xml to QTP:" + l_xmlString);
                }

                // URL�N���X�̃C���X�^���X�𐶐�
                ////���L�\�[�X�̓e�X�g�p�̂�
                URL l_postURL;
                if("HTTP".equalsIgnoreCase(GtlUtils.getTradingSystem().getPreference("qtp.rich.push.protocol.type")))
                {
                    l_postURL = new URL(l_strPushUrl);
                }else{
                    l_postURL = new URL(null, l_strPushUrl, new sun.net.www.protocol.https.Handler());
                }

                // �ڑ����܂�
                con = (HttpURLConnection) l_postURL.openConnection();
                // �o�͂��s���悤�ɐݒ肵�܂�
                con.setDoOutput(true);
                // ������ݒ肷��
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "text/xml;charset=" + ENCODE);
                con.setRequestProperty("Content-Length", String.valueOf(getByteLength(l_xmlString)));
                con.setRequestProperty("Connection", "Keep - Alive");
                
                if(l_userName != null || l_password != null)
                {
                    String l_basic = l_userName + ":" + l_password;
                    String encoding = new sun.misc.BASE64Encoder().encode(l_basic.getBytes());
                    con.setRequestProperty("Authorization", "Basic " + encoding);    
                }

                // �ڑ�
                con.connect();

                // �o�̓X�g���[�����擾
                l_out = con.getOutputStream();
                // �o�̓X�g���[����xml��������
                l_out.write(l_xmlString.getBytes(ENCODE));
                l_out.flush();
                l_out.close();
                l_out = null;

                // ���M���ʂ𔻒�
                String l_response_code = con.getHeaderField(0);
                log.debug("response code:" + l_response_code);
                // http�R�[�h��Ԃ�����ł���ꍇ
                if (l_response_code != null && l_response_code.indexOf(HTTP_OK_CODE) >= 0)
                {
                    l_is = new BufferedReader(new InputStreamReader(con.getInputStream(), ENCODE));

                    StringBuffer l_sb = new StringBuffer();

                    String l_line = null;
                    while ((l_line = l_is.readLine()) != null)
                    {
                        l_sb.append(l_line);
                    }

                    l_result = l_sb.toString();

                }
                else   //�G���[�̏ꍇ�A�x�����o��
                {   
                    log.warn("response code error:" + l_response_code);
                    l_is = new BufferedReader(new InputStreamReader(con.getInputStream(), ENCODE));

                    StringBuffer l_sb = new StringBuffer();

                    String l_line = null;
                    while ((l_line = l_is.readLine()) != null)
                    {
                        l_sb.append(l_line);
                    }
                    log.warn("response content:"+l_sb.toString());
                }

            }
            catch (Exception e)
            {
                log.error(e.getMessage(), e);

                if (con != null)
                {
                    if (e instanceof IOException)
                    {
                        try
                        {
                            InputStream is = con.getErrorStream();
                            if (is != null)
                            {
                                int ret = 0;
                                // read the response body
                                byte[] buf = new byte[1024];
                                while ((ret = is.read(buf)) > 0)
                                {
                                }
                                // close the errorstream
                                is.close();
                            }
                        }
                        catch (IOException ex)
                        {
                            log.error(ex.getMessage(), ex);
                        }
                    }
                    con.disconnect();
                }
            }
            finally
            {
                if (l_out != null)
                {
                    try
                    {
						l_out.close();
					}
                    catch (IOException e) 
                    {
                        log.error(e.getMessage(), e);
					}
                }
                if (l_is != null)
                {
                    try
                    {
                        l_is.close();
                    }
                    catch (IOException e1)
                    {
                        log.error(e1.getMessage(), e1);
                    }
                }
                //---�ȉ���javadoc�ɂ��Adisconnect���s���Ȃ�
                //�e HttpURLConnection �C���X�^���X���g�p���ĒP��̗v�����쐬���܂����A
                //HTTP �T�[�o�ւ̃l�b�g���[�N�ڑ��͑��̃C���X�^���X�Ɠ��ߓI�ɋ��L����邱�Ƃ�����܂��B
                //�v����AHttpURLConnection �� InputStream �܂��� OutputStream ��
                // close()
                //���\�b�h���Ăяo���ƁA���̃C���X�^���X�Ɋ֘A�����l�b�g���[�N���\�[�X���������܂����A
                //���L�����ڑ��ɂ͉e������܂���Bdisconnect() ���\�b�h���Ăяo���ƁA
                //�����ڑ������̎��A�C�h����Ԃł���΃\�P�b�g�����邱�Ƃ�����܂��B

                //connect���\�b�h�����F
                //���� URL ���Q�Ƃ��郊�\�[�X�ւ̒ʐM�����N���m�����܂� (�ʐM�����N���m������Ă��Ȃ��ꍇ)�B
                //connect ���\�b�h���Ăяo�����Ƃ��ɁA�ڑ������łɊm������Ă���� (connected �t�B�[���h�̒l��
                //true �ł����)�A���\�b�h�Ăяo���͖�������܂��B
//                if (con != null)
//                {
//                    con.disconnect();
//                }
            }
        }

        if (log.ison())
        {
            log.debug("Xml Response from QTP:" + l_result);
        }
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * �ʒm���M�P��xml�f�[�^�̊O�ɒʒm���Mxml��root�v�f��ǉ��B
     * 
     * �i��̑��M���b�Z�[�W�ɕ����̒ʒm���M�P�ʂ��܂܂�Ă��܂��B�j
     * 
     * @@param l_institution
     * @@param l_unitXml
     * @@return
     */
    private String getFullXmlString(String l_institution, String l_unitXml)
    {
        return WEB3QtpRichPushObjectToXMLConverter.XML_HEAD + WEB3QtpRichPushObjectToXMLConverter.XML_DESC_HEAD + l_institution
                + WEB3QtpRichPushObjectToXMLConverter.XML_DESC_TAIL + l_unitXml + WEB3QtpRichPushObjectToXMLConverter.XML_TAIL;
    }

    /**
     * ������̃o�C�g�����v�Z�BUTF-8�Ōv�Z�A�G���[������ꍇ�A�f�t�H�[���gencode
     * 
     * @@param l_str
     * @@return
     */
    private int getByteLength(String l_str)
    {
        int l_res = 0;
        try
        {
            l_res = l_str.getBytes(ENCODE).length;
        }
        catch (UnsupportedEncodingException e)
        {
            log.warn("UTF-8 not supported. Use default encode", e);
            l_res = l_str.getBytes().length;
        }
        if (log.ison())
        {
            log.debug("Byte length:" + l_res);
        }
        return l_res;
    }

    /**
     * getPushUrl ���Murl���擾
     * 
     * @@param String
     *            l_strInstitutionCode String
     * @@return String
     */
    private String getPushUrl(String l_strInstitutionCode)
    {

        final String STR_METHOD_NAME = "getPushUrl()";
        log.entering(STR_METHOD_NAME);

        log.debug("�v�b�V��URL����肷��L�[=" + STR_RICH_PUSH_URL_KEY + l_strInstitutionCode);
        String l_strPushUrl = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_URL_KEY + l_strInstitutionCode);

        log.debug("rich.push.url=" + l_strPushUrl);
        log.exiting(STR_METHOD_NAME);
        return l_strPushUrl;

    }

}@


1.1
log
@*** empty log message ***
@
text
@a383 2
                    //proxy�̍Đݒ�
                    weblogic.net.http.HttpClient.resetProperties();
@

