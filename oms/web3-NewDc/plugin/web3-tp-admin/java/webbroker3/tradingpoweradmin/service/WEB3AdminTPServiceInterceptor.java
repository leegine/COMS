head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminTPServiceInterceptor�N���X(WEB3AdminTPServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.service;

import java.lang.reflect.Method;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPOrderAccProductDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPProductCodeDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

/**
 * �]�͊Ǘ��҃T�[�r�X�C���^�[�Z�v�^
 *
 * ���ڍז���̂��߁A
 * �e�@@�\���ƂɎ�t���ԃ`�F�b�N�ݒ肪�قȂ�ꍇ
 * �T�[�r�X���ƂɃT�[�r�X�C���^�[�Z�v�^���`����\��B
 *
 */
public class WEB3AdminTPServiceInterceptor implements Interceptor
{
    /** ���O�o�̓��[�e�B���e�B */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPServiceInterceptor.class);

    /**
     * ����J�����_�R���e�L�X�g�̑�����
     */
    public static final String TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME =
        "webbroker3.tpadmin.attributes.tradingcalendarcontext";

    /**
     * ��t���Ԃ̑�����
     */
    public static final String TIMESTAMP_ATTRIBUTE_NAME =
        "webbroker3.tpadmin.attributes.timestamp";

    /**
     * �I�t�Z�b�g�̑�����
     */
    public static final String OFFSET_ATTRIBUTE_NAME =
        "webbroker3.tpadmin.attributes.offset";


   /**
    * @@roseuid 41DBCABF01D6
    */
   public WEB3AdminTPServiceInterceptor()
   {
   }

   /**
    * ���ڍז���
    *
    * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
    * �@@�|OpLoginSecurityService�̓��e��������ԃR���e�L�X�g��
    * �@@�@@�@@�v���p�e�B���Z�b�g����B
    *
    * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h  = �Ǘ���.get�،���ЃR�[�h()
    * �@@����J�����_�R���e�L�X�g.���X�R�[�h  = �Ǘ���.get���X�R�[�h()
    * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
    * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h00�FOTHER�h
    * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h00�FUNDEFINED�h
    * �@@����J�����_�R���e�L�X�g.������t���i = "0�FDEFAULT"
    * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h
    *
    * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�
    * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B
    * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
    *
    * �Q�j�@@��t�����A���t���[�����Z�b�g����B
    * �@@�|������ԊǗ�.setTimestamp()���R�[������B
    * @@roseuid 41B971650058
    */
   public Object onCall(Method arg0, Object[] arg1)
   {
       // �ݒ肳��Ă���ThreadLocal�������ړ�
       removeAttributes();

       //�Ǘ��҃I�u�W�F�N�g���擾
       // ����J�����_�R���e�L�X�g�𐶐�
       WEB3GentradeTradingClendarContext l_context =
           new WEB3GentradeTradingClendarContext();
       String l_strInstitutionCode = null; // �،���ЃR�[�h
       String l_strBranchCode = null; // ���X�R�[�h

       try
       {
           WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_strInstitutionCode = l_admin.getInstitutionCode();
           l_strBranchCode = l_admin.getBranchCode();

       }
       catch (WEB3SystemLayerException se)
       {
           log.error(se.getMessage(), se);
       }

       // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.get�،���ЃR�[�h()
       l_context.setInstitutionCode(l_strInstitutionCode);
       // ����J�����_�R���e�L�X�g.���X�R�[�h = �Ǘ���.get���X�R�[�h()
       l_context.setBranchCode(l_strBranchCode);
       // ����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
       l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
       // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h00�FDEFAULT�h
       l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
       // ����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
       l_context.setProductCode(WEB3AdminTPProductCodeDef.DEFAULT);
       // ����J�����_�R���e�L�X�g.������t���i = �h00�FDEFAULT�h
       l_context.setOrderAcceptProduct(WEB3AdminTPOrderAccProductDef.DEFAULT);
       // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h
       l_context.setOrderAcceptTransaction(
           WEB3OrderAccTransactionDef.REFERENCE);

       // ������ԃR���e�L�X�g���Z�b�g
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
           l_context);

       // ��t�����A���t���[�����Z�b�g
       try
       {
           WEB3GentradeTradingTimeManagement.setTimestamp();
       }
       catch (WEB3SystemLayerException sle)
       {
           log.error(sle.getMessage(), sle);
       }

       log.debug("--------------------------------------------------");
       log.debug("TradingCalendarContext.institutionCode=" + l_context.getInstitutionCode());
       log.debug("TradingCalendarContext.branchCode=" + l_context.getBranchCode());
       log.debug("TradingCalendarContext.MarketCode=" + l_context.getMarketCode());
       log.debug("TradingCalendarContext.tradingTimeType=" + l_context.getTradingTimeType());
       log.debug("TradingCalendarContext.productCode=" + l_context.getProductCode());
       log.debug("TradingCalendarContext.orderAcceptProduct=" + l_context.getOrderAcceptProduct());
       log.debug("TradingCalendarContext.orderAcceptTransaction=" + l_context.getOrderAcceptTransaction());
       log.debug("--------------------------------------------------");

       return null;



   }

   /**
    * �T�[�r�X���\�b�h�I�����ɃR�[�������B
    * ����J�����_�R���e�L�X�g�N���A�����B
    *
    * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
    *
    * ������ԊǗ�.TIMESTAMP_TAG
    * ������ԊǗ�.OFFSET_TAG
    * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
    * @@roseuid 41B97171027B
    */
   public void onReturn(Object arg0, Object arg1)
   {
       resetAttributes();
   }

   /**
    * �T�[�r�X���\�b�h�I�����ɃR�[�������B
    * ����J�����_�R���e�L�X�g�N���A�����B
    *
    * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
    *
    * ������ԊǗ�.TIMESTAMP_TAG
    * ������ԊǗ�.OFFSET_TAG
    * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
    * @@roseuid 41B9717502B9
    */
   public void onThrowable(Object arg0, Throwable arg1)
   {
       resetAttributes();
   }

   /**
    * �ݒ肳��Ă���ThreadLocal�������ړ�����
    */
   private void removeAttributes()
   {

       // �ݒ肳��Ă���ThreadLocal�������擾
       Object l_objTempTradingCalendarContext =
           ThreadLocalSystemAttributesRegistry.getAttribute(
               WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
       Object l_objTempTimestamp =
           ThreadLocalSystemAttributesRegistry.getAttribute(
               WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
       Object l_objTempOffset =
           ThreadLocalSystemAttributesRegistry.getAttribute(
               WEB3GentradeTradingTimeManagement.OFFSET_TAG);

       // �擾����ThreadLocal������ʂ̖��O�ŕۑ�
       ThreadLocalSystemAttributesRegistry.setAttribute(
           TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME,
           l_objTempTradingCalendarContext);
       ThreadLocalSystemAttributesRegistry.setAttribute(
           TIMESTAMP_ATTRIBUTE_NAME,
           l_objTempTimestamp);
       ThreadLocalSystemAttributesRegistry.setAttribute(
           OFFSET_ATTRIBUTE_NAME,
           l_objTempOffset);
   }

   /**
    * ThreadLocal�������Đݒ肷��B
    */
   private void resetAttributes()
   {

       // �ޔ����Ă����������擾
       Object l_objContext =
           ThreadLocalSystemAttributesRegistry.getAttribute(
               TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME);
       Object l_objTimestamp =
           ThreadLocalSystemAttributesRegistry.getAttribute(
               TIMESTAMP_ATTRIBUTE_NAME);
       Object l_objOffset =
           ThreadLocalSystemAttributesRegistry.getAttribute(
               OFFSET_ATTRIBUTE_NAME);

       // �擾�����������Đݒ�
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
           l_objContext);
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
           l_objTimestamp);
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.OFFSET_TAG,
           l_objOffset);

       // �ޔ����Ă����������폜
       ThreadLocalSystemAttributesRegistry.setAttribute(
           TRADING_CALENDAR_CONTEXT_ATTRIBUTE_NAME,
           null);
       ThreadLocalSystemAttributesRegistry.setAttribute(
           TIMESTAMP_ATTRIBUTE_NAME,
           null);
       ThreadLocalSystemAttributesRegistry.setAttribute(
           OFFSET_ATTRIBUTE_NAME,
           null);

   }



}
@
