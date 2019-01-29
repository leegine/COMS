head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeOrderSwitching.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������ؑ�(WEB3GentradeOrderSwitching.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/28 羐� (���u) �V�K�쐬
Revesion History : 2006/02/26 ������(���u) ���ʎd�l�ύX�E���f��No176
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChangeCancelEnableFlag;
import webbroker3.common.define.WEB3OrderEngineDivDef;
import webbroker3.common.define.WEB3SubmitMqTriggerDef;
import webbroker3.common.define.WEB3ValidFlag;
import webbroker3.gentrade.data.OrderSwitchingDao;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (������ؑ�)<BR>
 * �،���Ё{���i�{�s�ꖈ�̒����̔�����̐؂�ւ���\������B<BR>
 * <BR>
 * �iDB���C�A�E�g �u������ؑփe�[�u���d�l.xls�v�Q�Ɓj<BR>
 */
public class WEB3GentradeOrderSwitching implements BusinessObject
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeOrderSwitching.class);

    /**
     * ������ؑ�Row�I�u�W�F�N�g
     * �iDAO���������N���X�j
     */
    private OrderSwitchingRow orderSwitchingRow;

//    /**
//     * �R���X�g���N�^�B<BR>
//     * �����̏����Ɉ�v���锭����ؑփI�u�W�F�N�g��ԋp����B<BR> 
//     * <BR>
//     * this.������ؑ�(�،���ЃR�[�h, �����^�C�v, �s��R�[�h, <BR>
//     * �����o�H�敪, �t�����g�����V�X�e���敪)��<BR>
//     * delegate����B<BR>
//     * <BR>
//     * �������u�t�����g�����V�X�e���敪�v�ɂ�null���Z�b�g����B<BR>
//     * <BR> 
//     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
//     * @@param l_productType �����^�C�v
//     * @@param l_strMarketCode �s��R�[�h
//     * @@param l_strSubmitOrderRouteDiv �����o�H�敪
//     *    �i0�FSONAR���n  1�FSONAR���n  2�F�t�����g�������n  3�F�t�����g�������n�j<BR>
//     *     �������o�H�̒ǉ��ɔ����A�R�[�h�l���ǉ������B<BR>
//     * @@return WEB3GentradeOrderSwitching
//     * @@throws WEB3SystemLayerException
//     * @@roseuid 423FCE0A02B5
//     */
//    public WEB3GentradeOrderSwitching(
//        String l_strInstitutionCode,
//        ProductTypeEnum l_productType,
//        String l_strMarketCode,
//        String l_strSubmitOrderRouteDiv)
//        throws WEB3SystemLayerException
//    {
//        this(l_strInstitutionCode,
//            l_productType,
//            l_strMarketCode,
//            l_strSubmitOrderRouteDiv,
//            null);
//    }

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �{�I�u�W�F�N�g���C���X�^���X�����A<BR>
     * ������Row�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_orderSwitchingRow - ������ؑ�Row�I�u�W�F�N�g
     * @@return WEB3GentradeOrderSwitching
     * @@roseuid 423FD0D3032A
     */
    public WEB3GentradeOrderSwitching(OrderSwitchingRow l_orderSwitchingRow)
    {
        final String STR_METHOD_NAME = "WEB3GentradeOrderSwitching(OrderSwitchingRow)";
        if (l_orderSwitchingRow == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������ؑ�Row�I�u�W�F�N�g = null");
        }
        this.orderSwitchingRow = l_orderSwitchingRow;
    }

    /**
     * this.������ؑ�Row��ԋp����B
     * @@return java.lang.Object
     * @@roseuid 423FCE0A02B4
     */
    public java.lang.Object getDataSourceObject()
    {
        return this.orderSwitchingRow;
    }

    /**
     * (is��������\)<BR>
     * ����������\���ǂ�����Ԃ��B<BR>
     * <BR>
     * �Ethis.������ؑ�Row.�L���t���O == "ON"�̏ꍇ�́Atrue��Ԃ��B<BR>
     * �Ethis.������ؑ�Row.�L���t���O == "OFF"�̏ꍇ�́A�ȉ��̒ʂ�Ƃ���B<BR>
     * �@@�|this.������ؑ�Row.��������\�t���O == "�\"�̏ꍇ�́A<BR>
     *      true��Ԃ��B<BR>
     * �@@�|��L�ȊO�̏ꍇ�́Afalse��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 423FCFC60074
     */
    public boolean isChangeCancelEnable()
    {     
        //this.������ؑ�Row.�L���t���O == "ON"�̏ꍇ�́Atrue��Ԃ��B
        if(WEB3ValidFlag.ON.equals(this.orderSwitchingRow.getValidFlag()))
        {
            return true;
        }
        else
        {
            //this.������ؑ�Row.�L���t���O == "OFF"�̏ꍇ�́A�ȉ��̒ʂ�Ƃ���B
            // �@@�|this.������ؑ�Row.��������\�t���O == "�\"�̏ꍇ�́Atrue��Ԃ��B
            // �@@�|��L�ȊO�̏ꍇ�́Afalse��Ԃ��B
            if(WEB3ChangeCancelEnableFlag.ENABLE.equals(this.orderSwitchingRow.getChangeCancelEnableFlag()))
            {
                return true;
            }
            return false;
        }

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �����̏����Ɉ�v���锭����ؑփI�u�W�F�N�g��ԋp����B<BR> 
     *<BR>
     * �P�j�@@DB����<BR> 
     *  �����̒l�ɂĔ�����ؑփe�[�u������������B<BR> 
     *<BR>
     *�@@[����]<BR> 
     *�@@�@@�@@�@@�،���ЃR�[�h = �����̏،���ЃR�[�h<BR> 
     *�@@���@@�����^�C�v = �����̖����^�C�v <BR>
     *�@@���@@�s��R�[�h = �����̎s��R�[�h <BR>
     *�@@���@@�����o�H�敪 = �����̔����o�H�敪 <BR>
     *�@@���@@�t�����g�����V�X�e���敪 = �����̃t�����g�����V�X�e���敪<BR> 
     *<BR>
     *�@@�Y������f�[�^�����݂��Ȃ��ꍇ�́A<BR> 
     *�@@��O��throw����B <BR>
     *<BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR> 
     *�@@�������ʂ̍s�I�u�W�F�N�g�i������ؑ�Row�j���A<BR>
     *  this.������ؑ�Row�ɃZ�b�g����B<BR> 
     *<BR>
     * @@param l_strInstitutionCode �،���ЃR�[�h
     * @@param l_productType �����^�C�v
     * @@param l_strMarketCode �s��R�[�h
     * @@param l_strSubmitOrderRouteDiv �����o�H�敪 
     * @@param l_strFrontOrderSystemCode �t�����g�����V�X�e���敪
     * @@return WEB3GentradeOrderSwitching
     * @@throws WEB3SystemLayerException
     */
    public WEB3GentradeOrderSwitching(
        String l_strInstitutionCode,
        ProductTypeEnum l_productType,
        String l_strMarketCode,
        String l_strSubmitOrderRouteDiv,
        String l_strFrontOrderSystemCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeOrderSwitching(String, ProductTypeEnum, String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�����̒l�ɂĔ�����ؑփe�[�u������������B
            this.orderSwitchingRow =
                OrderSwitchingDao.findRowByPk(
                    l_strInstitutionCode,
                    l_productType,
                    l_strMarketCode,
                    l_strSubmitOrderRouteDiv,
                    l_strFrontOrderSystemCode
                    );
        }
        catch (DataFindException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeOrderSwitching.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        catch (DataNetworkException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeOrderSwitching.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        catch (DataQueryException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeOrderSwitching.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�L��������ؑ� )<BR>
     * �istatic���\�b�h�j <BR>
     * �w��ɊY������A�L���Ȕ�����ؑփI�u�W�F�N�g���擾����B<BR> 
     *<BR>
     *�P�j�@@DB����<BR> 
     *�@@������ؑփe�[�u�����ȉ��̏����Ō�������B<BR> 
     *�@@[����] <BR>
     *�@@�،���ЃR�[�h = �����̏،���ЃR�[�h<BR> 
     *�@@���@@�����^�C�v = �����̖����^�C�v <BR>
     *�@@���@@�s��R�[�h = �����̎s��R�[�h<BR>
     *  ���@@�t�����g�����V�X�e���敪 = �����̃t�����g�����V�X�e���敪<BR>
     *�@@���@@�L���t���O = "ON" <BR>
     *<BR>
     *�@@�Y������f�[�^�����݂��Ȃ��ꍇ�́Anull��Ԃ��B<BR> 
     *<BR>
     *�@@���v���郌�R�[�h�����������݂���ꍇ�́A<BR>
     * �u������ؑփe�[�u���ݒ�s���i�L���f�[�^���������݁j�v�̗�O��throw����B<BR>
     * <BR> 
     *�Q�j�@@�I�u�W�F�N�g����<BR> 
     * �P�j�Ŏ擾����������ؑ֍s�I�u�W�F�N�g���w�肵�A������ؑփI�u�W�F�N�g�𐶐�����B<BR> 
     *�@@���������I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode �،���ЃR�[�h
     * @@param l_strInstitutionCode �����^�C�v
     * @@param l_strMarketCode �s��R�[�h
     * @@param l_strFrontSystemOrderCode �t�����g�����V�X�e���敪
     * @@return WEB3GentradeOrderSwitching
     * @@throws WEB3SystemLayerException
     */
    public static WEB3GentradeOrderSwitching getOnOrderSwitching(
        String l_strInstitutionCode,
        ProductTypeEnum l_productType,
        String l_strMarketCode,
        String l_strFrontSystemOrderCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "getOnOrderSwitching(String, ProductTypeEnum, String, String)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeOrderSwitching l_return = null;
        try
        {
            List l_lstRecords;

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and product_type = ? ");
            l_sbWhere.append(" and market_code = ? ");
            l_sbWhere.append(" and front_order_system_code = ? ");
            l_sbWhere.append(" and valid_flag = ? ");

            List l_lstOrderSwitchingWhere = new ArrayList();
            l_lstOrderSwitchingWhere.add(l_strInstitutionCode);
            l_lstOrderSwitchingWhere.add(l_productType);
            l_lstOrderSwitchingWhere.add(l_strMarketCode);
            l_lstOrderSwitchingWhere.add(l_strFrontSystemOrderCode);
            l_lstOrderSwitchingWhere.add(WEB3ValidFlag.ON);

            Object[] l_objOrderSwitchingWhere = 
                new Object[l_lstOrderSwitchingWhere.size()];
            l_lstOrderSwitchingWhere.toArray(l_objOrderSwitchingWhere);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                OrderSwitchingRow.TYPE,
                l_sbWhere.toString(),
                l_objOrderSwitchingWhere);

            //�Y������f�[�^�����݂��Ȃ��ꍇ�́Anull��Ԃ��B 
            int l_intSize = l_lstRecords.size();
            if (l_intSize == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //���v���郌�R�[�h�����������݂���ꍇ�́A 
            // �u������ؑփe�[�u���ݒ�s���i�L���f�[�^���������݁j�v�̗�O��throw����B 
            if (l_intSize > 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02187,
                    WEB3GentradeOrderSwitching.class.getName() + "." + STR_METHOD_NAME,
                    "�u������ؑփe�[�u���ݒ�s���i�L���f�[�^���������݁j�v�̗�O��throw����B"
                    );
            }
            
            l_return = 
                new WEB3GentradeOrderSwitching((OrderSwitchingRow)l_lstRecords.get(0));
        }
        catch (DataNetworkException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeOrderSwitching.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        catch (DataQueryException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeOrderSwitching.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);
        return l_return;
    }

    /**
     * (isMQ�g���K���s�o�H )<BR>
     * �w��̔����o�H��MQ�g���K���s�v���ǂ����𔻒肵�Ԃ��B<BR> 
     * �|�S�o�H��OFF�̏ꍇ�i�������̔����o�H�敪 == null�j�́Afalse��Ԃ��B<BR> 
     * �|��L�ȊO�́A�w��̌o�H��MQ�g���K���s�v�ۂ�Ԃ��B <BR>
     *<BR>
     *�P�j�@@�����̔����o�H�敪 == null�̏ꍇ�́Afalse��ԋp����B<BR> 
     *�@@�@@�ȊO�A�ȉ��̏������s���B <BR>
     *<BR>
     *�Q�j�@@������ؑփN���X�̃C���X�^���X�𐶐�����B<BR> 
     *<BR>
     *�@@�@@----------------------------------------------------------<BR> 
     *�@@�@@��������ؑցF�R���X�g���N�^�����ݒ�d�l�� <BR>
     *<BR>
     *�@@�@@�،���ЃR�[�h�F�@@�����̓�����<BR> 
     *�@@�@@�����^�C�v�F�@@�����̓����� <BR>
     *�@@�@@�s��R�[�h�F�@@�����̓����� <BR>
     *�@@�@@�����o�H�敪�F�@@�����̓����� <BR>
     *�@@�@@�t�����g�����V�X�e���敪�F�@@�����̓�����<BR> 
     *�@@�@@----------------------------------------------------------<BR> 
     *<BR>
     *�R�j�@@�擾�����C���X�^���X.MQ�g���K���s == "MQ�g���K�����{����"�̏ꍇ��true���A<BR> 
     *�@@�@@�@@"MQ�g���K�����{���Ȃ�"�̏ꍇ��false���A���ꂼ��ԋp����B   <BR>
�@@�@@ *�@@���Y���f�[�^�Ȃ��̏ꍇ��true��ԋp����B<BR>
�@@�@@ *�@@���i�Y���f�[�^�Ȃ��́ASONAR���͂̃t�����g�����Ή��s��w��̒����̏ꍇ�݂̂��肦��j<BR>
     *<BR>
     * @@param l_strInstitutionCode �،���ЃR�[�h
     * @@param l_productType �����^�C�v
     * @@param l_strMarketCode �s��R�[�h
     * @@param l_strSubmitOrderRouteDiv �����o�H�敪
     * @@param l_strFrontOrderSystemCode �t�����g�����V�X�e���敪
     * @@return boolean
     * @@throws WEB3SystemLayerException
     */
    public static boolean isSubmitMQTriggerEnable(
        String l_strInstitutionCode,
        ProductTypeEnum l_productType,
        String l_strMarketCode,
        String l_strSubmitOrderRouteDiv,
        String l_strFrontOrderSystemCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "isSubmitMQTriggerEnable(String, ProductTypeEnum, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�|�S�o�H��OFF�̏ꍇ�i�������̔����o�H�敪 == null�j�́Afalse��Ԃ��B 
        if (l_strSubmitOrderRouteDiv == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        try
        {
            WEB3GentradeOrderSwitching l_OrderSwitching = 
                new WEB3GentradeOrderSwitching(
                    l_strInstitutionCode,
                    l_productType,
                    l_strMarketCode,
                    l_strSubmitOrderRouteDiv,
                    l_strFrontOrderSystemCode
                    );
                    
            String l_strSubmitMqTrigger = 
            l_OrderSwitching.orderSwitchingRow.getSubmitMqTrigger();
            
            //�擾�����C���X�^���X.MQ�g���K���s == "MQ�g���K�����{����"�̏ꍇ��true��ԋp����B 
            if (WEB3SubmitMqTriggerDef.TRIGGER.equals(l_strSubmitMqTrigger))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            //"MQ�g���K�����{���Ȃ�"�̏ꍇ��false���A���ꂼ��ԋp����B  
            else if (WEB3SubmitMqTriggerDef.NOT_TRIGGER.equals(l_strSubmitMqTrigger))
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    WEB3GentradeOrderSwitching.class.getName()
                    + "." + STR_METHOD_NAME);
            }
        }
        catch (WEB3SystemLayerException l_e)
        {
            //���Y���f�[�^�Ȃ��̏ꍇ��true��ԋp����B
            //���i�Y���f�[�^�Ȃ��́ASONAR���͂̃t�����g�����Ή��s��w��̒����̏ꍇ�݂̂��肦��j
            if(WEB3ErrorCatalog.SYSTEM_ERROR_80005.equals(l_e.getErrorInfo()))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }    
            
            throw new WEB3SystemLayerException(
                l_e.getErrorInfo(),
                WEB3GentradeTradingTimeManagement.class.getName() + "." 
                    + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
    }
    
    /**
     * (isSONAR)<BR>
     * �����o�H�̃G���W����SONAR���ǂ�����Ԃ��B<BR>
     * <BR>
     * �Ethis.������ؑ�Row.�����G���W���敪 == "SONAR"�̏ꍇ�́Atrue��Ԃ��B<BR>
     * �Ethis.������ؑ�Row.�����G���W���敪 != "SONAR"�̏ꍇ�́Afalse��Ԃ��B<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 423FCFC60074
     */
    public boolean isSONAR()
    {
        final String STR_METHOD_NAME = "isSONAR()";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3OrderEngineDivDef.SONAR.equals(this.orderSwitchingRow.getOrderEngineDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
