head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeFinObjectManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g�����Z�I�u�W�F�N�g�}�l�[�W��(WEB3GentradeFinObjectManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/13 �����@@���F(SRA) �V�K�쐬
Revesion History : 2004/08/25 羐� (���u) �ύX RuntimeSystemException --> WEB3BaseRuntimeException
Revesion History : 2004/10/09 �Г� (���u) getMarketBySONAR(String , String )��ǉ�
Revesion History : 2005/07/07 �Г� (���u) getFeqMarkets(String)��ǉ�
Revesion History : 2005/07/07 �Г� (���u) getOpenFeqMarkets(String)��ǉ�
Revesion History : 2008/02/01 �h�C (���u) getLinkFeqMarkets(String)��ǉ�
*/
package webbroker3.gentrade;

import java.util.List;
import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.FinObjectManagerImpl;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g�����Z�I�u�W�F�N�g�}�l�[�W��)<BR>
 *<BR> 
 * ���Z�I�u�W�F�N�g�i�s��j�ɑ΂��鑀���\�����܂��B<BR>
 * xTrade��FinObjectManager���g�������N���X�B<BR>
 *<BR> 
 * @@author �����@@���F(SRA)
 * @@version 1.0
 */
public class WEB3GentradeFinObjectManager extends FinObjectManagerImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeFinObjectManager.class);

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3GentradeFinObjectManager()
    {
        super();
    }

    /**
     * (get�s��)<BR>
     *<BR> 
     * �،���ЃR�[�h�A�s��R�[�h���s��I�u�W�F�N�g���擾���܂��B<BR>
     * �igetMarket�̃I�[�o�[���[�h�j<BR>
     * @@param l_institutionCode �،���ЃR�[�h
     * @@param l_marketCode �s��R�[�h
     * @@return �s��I�u�W�F�N�g
     * @@throws NotFoundException �Y������f�[�^��������Ȃ������ꍇ
     */
    public Market getMarket(String l_strInstitutionCode, String l_strMarketCode)
        throws NotFoundException
    {
        String l_strMethodName = "getMarket(String, String)";
        log.entering(l_strMethodName);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountMgr = l_finApp.getAccountManager();
        Institution l_institution = l_accountMgr.getInstitution(l_strInstitutionCode);

        log.exiting(l_strMethodName);

        return getMarket(l_institution, l_strMarketCode);
    }

    /**
     * �،���ЃI�u�W�F�N�g�A�s��R�[�h���s��I�u�W�F�N�g���擾���܂��B<BR>
     * �igetMarket�̃I�[�o�[���[�h�j<BR>
     * @@param l_institution �،���ЃI�u�W�F�N�g
     * @@param l_strMarketCode �s��R�[�h
     * @@return �s��I�u�W�F�N�g
     * @@throws NotFoundException �Y������f�[�^��������Ȃ������ꍇ
     */
    public Market getMarket(Institution l_institution, String l_strMarketCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getMarket(Institution, String)";
        
        try
        {
            return new WEB3GentradeMarket(l_institution, l_strMarketCode);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No Market object found with the given code : " + l_strMarketCode);
        }
        catch (DataException de)
        {
            String msg = "Error while getting Market Object for code : " + l_strMarketCode;
            log.error(msg, de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * �s��ID���s��I�u�W�F�N�g���擾���܂��B<BR>
     * �igetMarket�̃I�[�o�[���[�h�j<BR>
     * @@param l_lngMarketId �s��ID
     * @@return �s��I�u�W�F�N�g
     * @@throws NotFoundException �Y������f�[�^��������Ȃ������ꍇ
     */
    public Market getMarket(long l_lngMarketId)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getMarket(long)";
        
        try
        {
            return new WEB3GentradeMarket(l_lngMarketId);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No Market object found with the given id : " + l_lngMarketId);
        }
        catch (DataException de)
        {
            String msg = "Error while getting Market Object for code : " + l_lngMarketId;
            log.error(msg, de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * �s��I�u�W�F�N�g�̔z���Ԃ��B<BR>
     * �igetMarkets�̃I�[�o�[���[�h�j<BR>
     *<BR> 
     * @@return �s��I�u�W�F�N�g�̔z��
     */
    public Market[] getMarkets() 
    {
        final String STR_METHOD_NAME = "getMarkets()";

        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(MarketRow.TYPE, null, "market_id", null, null);
            int l_intSize = l_lisRows.size();
            Market markets[] = new Market[l_intSize];
            for (int i = 0; i < l_intSize; i++)
            {
                markets[i] = new WEB3GentradeMarket(((MarketRow)l_lisRows.get(i)).getMarketId());
            }

            return markets;
        }
        catch (DataException de)
        {
            log.error("Exception while fetching all rows in market_table. ", de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

    }

    /**
     * �g���[�_ID��舵�҃I�u�W�F�N�g��Ԃ��B<BR>
     * �igetTrader�̃I�[�o�[���[�h�j<BR>
     *<BR> 
     * @@param l_lngTraderId �g���[�_ID
     * @@return ���҃I�u�W�F�N�g
     * @@throws NotFoundException �Y������f�[�^��������Ȃ������ꍇ
     */
    public Trader getTrader(long l_lngTraderId)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getTrader(long)";
        
        try
        {
            return new WEB3GentradeTrader(l_lngTraderId, false);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No Trader  object found with the trader id : " + l_lngTraderId);
        }
        catch (DataException de)
        {
            String msg = "Error while getting Trader  Object for id : " + l_lngTraderId;
            log.error(msg, de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * �،���ЃI�u�W�F�N�g�A�g���[�_�R�[�h�A���X�R�[�h��舵�҃I�u�W�F�N�g��Ԃ��B<BR>
     * �igetTrader�̃I�[�o�[���[�h�j<BR>
     *<BR> 
     * @@param l_inst �،���ЃI�u�W�F�N�g
     * @@param l_strTraderCode �g���[�_�R�[�h
     * @@param l_strBranchCode ���X�R�[�h
     * @@return ���҃I�u�W�F�N�g
     * @@throws NotFoundException �Y������f�[�^��������Ȃ������ꍇ
     */
    public Trader getTrader(Institution l_inst, String l_strTraderCode, String l_strBranchCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getTrader(Institution, String, String)";

        try
        {
            return new WEB3GentradeTrader(l_inst, l_strTraderCode, l_strBranchCode);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No Trader  object found with the InstitutionCode,trader code, branchCode : " +
                l_inst.getInstitutionCode() + "," + l_strTraderCode + "," + l_strBranchCode);
        }
        catch (DataException de)
        {
            String msg = "Error while getting InstitutionCode,Trader code, branchCode : " +
                l_inst.getInstitutionCode() + "," + l_strTraderCode + "," + l_strBranchCode;
            log.error(msg, de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * ���O�C��ID��舵�҃I�u�W�F�N�g��Ԃ��B<BR>
     * �igetTraderByLoginId�̃I�[�o�[���[�h�j<BR>
     *<BR> 
     * @@param l_lngLoginId ���O�C��ID
     * @@return ���҃I�u�W�F�N�g
     * @@throws NotFoundException �Y������f�[�^��������Ȃ������ꍇ
     */
    public Trader getTraderByLoginId(long l_lngLoginId)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getTraderByLoginId(long)";

        try
        {
            return new WEB3GentradeTrader(l_lngLoginId, true);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException("No Trader  object found with the login id : " + l_lngLoginId);
        }
        catch (DataException de)
        {
            String msg = "Error while getting Trader  Object for login id : " + l_lngLoginId;
            log.error(msg, de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * (get�s��BySONAR)<BR>
     * <BR>
     * �����̏،���ЃR�[�h�A�s��R�[�h(SONAR)���s��I�u�W�F�N�g���擾���܂��B<BR>
     * <BR>
     * �P�j�@@�s��}�X�^�e�[�u������A�ȉ��̏����ɍ��v����f�[�^���擾����B<BR>
     * <BR>
     * �@@�@@�@@�����o������<BR>
     * �@@�@@�@@�،���ЃR�[�h�@@���@@�����̏،���ЃR�[�h<BR>
     * �@@�@@�@@���@@�s��R�[�h(SONAR)�@@���@@�����̎s��R�[�h(SONAR)<BR>
     * <BR>
     * �@@�@@�@@�Y���f�[�^�Ȃ��̏ꍇ�A�܂��͊Y���f�[�^�������̏ꍇ�́A��O��throw����B<BR>
     * <BR>
     * �Q�j�@@�擾�����s��I�u�W�F�N�g��Ԃ��B<BR> 
     *<BR> 
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strMarketCodeSONAR - (�s��R�[�h(SONAR))
     * @@return �s��I�u�W�F�N�g
     */
    public Market getMarketBySONAR(String l_strInstitutionCode, String l_strMarketCodeSONAR)
        throws WEB3SystemLayerException
    {

        final String STR_METHOD_NAME = "getMarketBySONAR(String, String)";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeMarket l_genMarket = null;
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisRecords = null;

            //�����o������
            //�،���ЃR�[�h�@@���@@�����̏،���ЃR�[�h
            //���@@�s��R�[�h(SONAR)�@@���@@�����̎s��R�[�h(SONAR)
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    MarketRow.TYPE,
                    "institution_code = ? and sonar_market_code = ?",
                    new Object[] {l_strInstitutionCode, l_strMarketCodeSONAR});

            //�Y���f�[�^�Ȃ��̏ꍇ�A�܂��͊Y���f�[�^�������̏ꍇ�́A��O��throw����B
            if(l_lisRecords.size() == 0)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else if(l_lisRecords.size() > 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            MarketRow l_marketRow = (MarketRow)l_lisRecords.get(0);
            
            l_genMarket = new WEB3GentradeMarket(l_marketRow.getMarketId());
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);                
        return l_genMarket;        
    }

    /**
     * (get�O���s��)<BR>
     *�igetFeqMarkets�j<BR>
     * <BR>
     * �O���s����擾����B<BR>
     * <BR>
     * �ȉ��̏����ɓ��Ă͂܂�s��s�i�FMarketParams�j��<BR>
     * �s��e�[�u�����擾����B <BR>
     * <BR>
     * [����]<BR>
     * �،���ЃR�[�h�F�@@�،���ЃR�[�h<BR>
     * �s��R�[�h�F�@@���A���t�@@�x�b�g�Ŏn�܂�s��R�[�h<BR>
     * <BR>
     * �擾�����s��s�ɂĎs��I�u�W�F�N�g�𐶐����z��ɂĕԋp����B<BR>
     * <BR> 
     * @@param l_strInstitutionCode (�،���ЃR�[�h)
     * @@return Market[]
     * @@throws WEB3SystemLayerException
     */
    public Market[] getFeqMarkets(String l_strInstitutionCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getFeqMarkets(String)";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeMarket[] l_genMarkets = null;
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisRecords = null;

            // [����]
            // �،���ЃR�[�h�F�@@�،���ЃR�[�h
            // �s��R�[�h�F�@@���A���t�@@�x�b�g�Ŏn�܂�s��R�[�h
            //institution_code = ? and (lower(substr(market_code,1,1)) >= 'a' and 
            //lower(substr(market_code,1,1)) <= 'z')
            String l_strWhere = "institution_code = ? and " +
                "(lower(substr(market_code, ?, ?)) >= ? and " + 
                "lower(substr(market_code, ?, ?)) <= ?) ";
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    MarketRow.TYPE,
                    l_strWhere,
                    new Object[] {
                        l_strInstitutionCode,
                        new Integer(1),
                        new Integer(1),
                        "a", 
                        new Integer(1),
                        new Integer(1),
                        "z"});

            //�Y���f�[�^�Ȃ��̏ꍇ�A��O��throw����B
            if((l_lisRecords == null) || (l_lisRecords.isEmpty()))
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            int l_intSize = l_lisRecords.size();
            l_genMarkets = new WEB3GentradeMarket[l_intSize];

            MarketRow l_marketRow;
            for(int i = 0; i < l_intSize; i++)
            {
                l_marketRow = (MarketRow)l_lisRecords.get(i);
                l_genMarkets[i] = 
                    new WEB3GentradeMarket(l_marketRow.getMarketId());
            }
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);                

        return l_genMarkets;
    }

    /**
     * (get�戵�\�O���s��)<BR>
     *�igetOpenFeqMarkets�j<BR>
     * <BR>
     * �戵�\�ȊO���s����擾����B<BR>
     * <BR>
     * this.get�O���s��()�̖߂�l���A<BR>
     * �����~�łȂ����́i�s��.is�����~ == false�j��z��ɂĕԋp����B<BR> 
     * <BR> 
     * @@param l_strInstitutionCode (�،���ЃR�[�h)
     * @@return Market[]
     * @@throws WEB3SystemLayerException
     */
    public Market[] getOpenFeqMarkets(String l_strInstitutionCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOpenFeqMarkets(String)";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeMarket[] l_genMarkets = null;

        ArrayList l_lisOpenFeqMarkets = new ArrayList();

        l_genMarkets = 
            (WEB3GentradeMarket[])this.getFeqMarkets(l_strInstitutionCode);

        int l_intSize = l_genMarkets.length;

        for(int i = 0; i < l_intSize; i++)
        {
            if(!l_genMarkets[i].isSuspension())
            {
                l_lisOpenFeqMarkets.add(l_genMarkets[i]);
            }
        }

        WEB3GentradeMarket[] l_returnMarkets = 
            new WEB3GentradeMarket[l_lisOpenFeqMarkets.size()];
        
        l_lisOpenFeqMarkets.toArray(l_returnMarkets);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_returnMarkets;
    }

    /**
     * (get�s��A���O���s��)<BR>
     *�igetLinkFeqMarkets�j<BR>
     * <BR>
     * �O���s��̂����A�s��A���̂��̂��擾����B <BR>
     * <BR>
     * �P�jthis.get�O���s��()���R�[������B <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@����.�،���ЃR�[�h <BR>
     * <BR>
     * �Q�j�P�Ŏ擾�����s��̂����A <BR>
     * �@@�@@�s��v���t�@@�����X�e�[�u��.�s��A���敪���R�[�h�����݂�����̂��擾���A <BR>
     * �@@�@@���ʂ�z��Ƃ��ĕԋp����B <BR>
     * <BR>
     * �@@�@@�@@[ �s��v���t�@@�����X�e�[�u���@@�������� ] <BR>
     * �@@�@@�@@�@@�e�[�u��.�s��ID�@@=�@@1�̖߂�l.getMarketId() <BR>
     * �@@�@@�@@�@@�v���t�@@�����X���ږ��@@=�@@feq.sle.broker <BR>
     * �@@�@@�@@�@@���ږ��A�ԁ@@=�@@�P <BR>
     * <BR>
     * @@param l_strInstitutionCode (�،���ЃR�[�h)
     * @@return Market[]
     * @@throws WEB3SystemLayerException
     */
    public Market[] getLinkFeqMarkets(String l_strInstitutionCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getLinkFeqMarkets(String)";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeMarket[] l_genMarkets = null;

        ArrayList l_lisLinkFeqMarkets = new ArrayList();

        l_genMarkets =
            (WEB3GentradeMarket[])this.getFeqMarkets(l_strInstitutionCode);

        int l_intSize = l_genMarkets.length;

        for(int i = 0; i < l_intSize; i++)
        {
            MarketPreferencesRow l_row = null;
            long l_lngMarketId = l_genMarkets[i].getMarketId();
            String l_strPreferencesName = WEB3MarketPreferencesNameDef.FEQ_SLE_BROKER;
            try
            {
                l_row = MarketPreferencesDao.findRowByPk(
                    l_lngMarketId,
                    l_strPreferencesName,
                    1);
            }
            catch (DataFindException l_ex)
            {
                log.debug("���̎s��͎s��A���Ώۂł͂���܂���(�s��R�[�h: "
                    + l_genMarkets[i].getMarketCode() + ")");
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            if (l_row != null)
            {
                l_lisLinkFeqMarkets.add(l_genMarkets[i]);
            }
        }

        WEB3GentradeMarket[] l_returnMarkets =
            new WEB3GentradeMarket[l_lisLinkFeqMarkets.size()];

        l_lisLinkFeqMarkets.toArray(l_returnMarkets);

        log.exiting(STR_METHOD_NAME);

        return l_returnMarkets;
    }
}
@
