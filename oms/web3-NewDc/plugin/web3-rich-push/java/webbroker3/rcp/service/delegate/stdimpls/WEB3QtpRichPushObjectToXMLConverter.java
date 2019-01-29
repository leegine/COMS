head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.33.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRichPushObjectToXMLConverter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@QTP���b�`�N���C�A���g�v�b�V�����b�Z�[�WXML�R���o�[�^�[(WEB3QtpRichPushObjectToXMLConverter.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 ��(FLJ) �V�K�쐬
                  : 2009/06/03 ��(FTL) ���Ή�
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.lang.reflect.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import webbroker3.util.*;

/**
 * QTP���b�`�N���C�A���g�v�b�V�����b�Z�[�WXML�R���o�[�^�[
 * @@author  : ��(FLJ)
 * @@version : 1.0
 */
public final class WEB3QtpRichPushObjectToXMLConverter
{

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QtpRichPushObjectToXMLConverter.class);

    /** XML HEAD */
    protected static final String XML_HEAD =
        "<?xml version='1.0' encoding='UTF-8'?>\n<!DOCTYPE tlg SYSTEM \"QnsVer1.dtd\">\n<tlg>\n";

    /** XML DESC HEAD */
    protected static final String XML_DESC_HEAD =
        "<dsc cdsc=\"";

    /** XML DESC TAIL */
    protected static final String XML_DESC_TAIL =
        "\" srvdsc=\"Apl\"/>\n";

    /** XML TAIL */
    protected static final String XML_TAIL =
        "</tlg>\n";

    /**
     * XML TEMPLETE
     */
    public static final String XML_TEMPLETE = "<ntc>\n"+
            "<nhd srlnum=\"_srlnum_\" sid=\"_sid_\" tm=\"_tm_\" knd=\"srv\"/>\n" +
            "<nmsg imp=\"1\">\n" +
            	"<title url=\"qtp:CMD=OPEN,DLG=TradingPanel,CONTENTS=_url__urlParams_\">\n" +
            	"_title_</title>\n" +
           	 	"<quote qcode=\"qtp:CMD=HOTKEY_qcodeParams_\">_qcodeName_</quote>\n" +
           		"_orderTypeName_\n" +
           		"<prc>_prc_</prc>_quantityText_\n" +
           		"<vol>_vol_</vol>\n" +
           		"<lnk url=\"qtp:CMD=OPEN,DLG=TradingPanel,CONTENTS=_url__urlParams_\">_lnkText_</lnk>\n" +
           		"<ftag>_tlgNtcNmsgFtagFatt_</ftag>\n" +
           "</nmsg>\n" +
	"</ntc>\n";

    /**
     * ���X�|���X��͗p�̃p�^��
     */
    public static final Pattern RESPONSE_PATTERN = Pattern.compile("<nhd srlnum=\"([0-9]+)\" sid=\"([A-Za-z0-9]+)\" tm=\"([0-9]+)\" knd=\"srv\"/>[\\x00-\\xff\\u25A0-\\uFFFF]+?<status>([A-Z_]+)</status>");

    /**
     * �I�u�W�F�N�g��XML�t�H�[�}�b�g�ϊ�
     * 
     * 
     * �߂�l�F	����null�̏ꍇ�A�߂�l���@@""
     * 			�I�u�W�F�N�g���ڎ擾���s�̏ꍇ�@@�߂�l���@@""�@@
     * 			�K�v�ȍ��ڂɂ�null������ꍇ�A�߂�l���@@""
     *
     * @@param l_obj Object 
     * @@return String
     */
    public static String toXML(Object l_obj)
    {
        final String STR_METHOD_NAME = "toXML()";
        log.entering(STR_METHOD_NAME);
        String l_result = "";
        //������null�łȂ��@@�ꍇ�Axml���쐬
        if (l_obj != null)
        {
            l_result = XML_TEMPLETE;
            //���ׂẴt�B�[���h���擾
            Field[] l_fields = l_obj.getClass().getDeclaredFields();
            for(int i = 0; i< l_fields.length ; i++)
            {
                l_fields[i].setAccessible(true);
                try
                {
                    //�t�B�[���h�̒l���擾
                    Object l_fValue = l_fields[i].get(l_obj);
                    String l_fName = "_"+l_fields[i].getName()+"_";
                    if(l_result.indexOf(l_fName) >0)
                    {
                        if (l_fValue != null && l_fValue instanceof String)
                        {
                            //null�łȂ��ꍇ�Atemplate�̓������O�̕�����u������
                            l_result = l_result.replaceAll("_"+l_fields[i].getName()+"_", (String)l_fValue );
                        }
                        else
                        {
                            // null �̏ꍇ�A�u""�v��template�Ɠ����L�[������u������
                            l_result = l_result.replaceAll("_"+l_fields[i].getName()+"_", "" );
                            continue;
                        }
                    }
                }
                catch (Exception ex)
                {
                    //�t�B�[���h�擾���s�̏ꍇ�A���ʂ�""�ɐݒ肵�āA�Ԃ�
                    log.error(ex.getMessage(), ex);
                    l_result = "";
                    break;
                }
            }
       }
       else
       {
           log.warn("Null Object. Build xml skip.");
       }

        log.exiting(STR_METHOD_NAME);
        return l_result;

    }

    /**
     * ���ʃ��X�|���X����͂��āA���ʃX�e�[�^�X��Map�ɕۑ��A�L�[�̓��N�G�X�g�̃��b�Z�[�W�I�u�W�F�N�g
     * 
     * �߂�l�F�@@����������́@@null �̏ꍇ�A�߂�l�@@�T�C�Y0�@@��Map�I�u�W�F�N�g
     *
     * @@param l_obj Object
     * @@return String
     */
    public static Map parseResult(String l_res)
    {
        final String STR_METHOD_NAME = "parseResult()";
        log.entering(STR_METHOD_NAME);

        Map l_result = new HashMap();
        //�������ʃ��X�|���X������null�ł���ꍇ�A�x���o��
        if (l_res == null)
        {
            log.warn("Response message is null. Can't parse result.");
        }
        else
        {
            //������p�^�����}�b�`
            Matcher l_matcher = RESPONSE_PATTERN.matcher(l_res);
            while(l_matcher.find())
            {
                //�p�^�����������ꍇ
                //�L�[�I�u�W�F�N�g���쐬
                WEB3QtpExcutionInformUnit l_unit = new WEB3QtpExcutionInformUnit();

                l_unit.setSrlnum(l_matcher.group(1));
                l_unit.setSid(l_matcher.group(2));
                l_unit.setTm(l_matcher.group(3));

                //�X�e�[�^�X���擾
                String l_status = l_matcher.group(4);

                //�}�b�v�ɕۑ�
                l_result.put(l_unit,l_status);
            }
        }
        if(l_result.size() == 0)
        {
            log.warn("Not Found Unit Result String. All Response Error :" + l_res);
        }

        log.exiting(STR_METHOD_NAME);
        return l_result;

    }
}
@
