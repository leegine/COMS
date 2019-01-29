head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderEmpCodeManageServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������^�p�R�[�h�̔ԃT�[�r�XImpl(WEB3FeqOrderEmpCodeManageServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  䈋�(���u) �V�K�쐬
                 : 2007/01/09 SRA��c ��QK00025�i���f��No.330�j
Revesion History : 2009/08/03  ���g(���u) �d�l�ύX���f��No.502
*/
package webbroker3.feq.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.data.FeqOrderEmpNumberParams;
import webbroker3.feq.data.FeqOrderEmpNumberRow;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeManageService;
import webbroker3.feq.util.WEB3FeqStringUtility;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������^�p�R�[�h�̔ԃT�[�r�XImpl) <BR>
 * �O�������^�p�R�[�h�̔ԃT�[�r�X�����N���X
 * 
 * @@author 䈋�
 * @@version 1.0 
 */
public class WEB3FeqOrderEmpCodeManageServiceImpl implements WEB3FeqOrderEmpCodeManageService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderEmpCodeManageServiceImpl.class);
    /**
     * @@roseuid 42CE39F501F4
     */
    public WEB3FeqOrderEmpCodeManageServiceImpl() 
    {
     
    }
    
    /**
     * (get�V�K�^�p�R�[�h) <BR>
     * �^�p�R�[�h�������̔Ԃ���B  <BR>
     *  <BR>
     * �^�p�R�[�h�̃R�[�h�̌n�͈ȉ��̒ʂ�B  <BR>
     *  <BR>
     * �@@�E�P�C�Q���ځiindex=0�`1�j�F<BR>
     * �@@�O�������^�p�R�[�h�擾�T�[�r�X.getPREFIX�i����.�،���ЃR�[�h�j�̖߂�l<BR>
     * �@@�E�R���ځiindex=2�j�F�@@�^�p�R�[�h�s�ꎯ�ʋ敪�i�s��}�X�^�e�[�u���j  <BR>
     * �@@�E�S�`�V���ځiindex=3�`6�j�F�@@ �S���̒ʔ� <BR>
     *  <BR>
     * �P�j�O�������^�p�R�[�h�̔�TransactionCallback�N���X�𐶐����A<BR>
     * �@@�@@��������TransactionCallback�N���X��doTransaction()�����s<BR>
     * �@@�@@�^�p�R�[�h�̐V�K�̔ԁA�擾����<BR>
     *  <BR>
     * �@@�@@�R���X�g���N�^�̈���<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�@@�@@�@@����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�^�p�R�[�h�s�ꎯ�ʋ敪�F����.�^�p�R�[�h�s�ꎯ�ʋ敪<BR>
     * �@@�@@�@@�������F�@@�@@�@@�@@�@@�@@�@@�@@����.������<BR>
     * �@@�@@doTransaction()�̃g�����U�N�V���������FTX_JOIN_EXISTING<BR>
     *  <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strFeqOrderEmpDiv - (�^�p�R�[�h�s�ꎯ�ʋ敪)
     * @@param l_datBizDate - (������)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 42845BE000AC
     */
    public String getNewEmpCode(String l_strInstitutionCode, 
        String l_strFeqOrderEmpDiv, Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNewEmpCode(String l_strInstitutionCode, ]" +
                "String l_strFeqOrderEmpDiv, Date l_datBizDate) ";
        log.entering(STR_METHOD_NAME);

        String l_strNewEmpCode;
        try
        {
            // �P�j�O�������^�p�R�[�h�̔�TransactionCallback�N���X�𐶐����A
            // �@@�@@��������TransactionCallback�N���X��doTransaction()�����s
            // �@@�@@�^�p�R�[�h���擾����
            WEB3FeqOrderEmpCodeManageTransactionCallback l_transactionCallback =
                new WEB3FeqOrderEmpCodeManageTransactionCallback(
                    l_strInstitutionCode, l_strFeqOrderEmpDiv, l_datBizDate);
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_strNewEmpCode = (String) l_processor.doTransaction(
                QueryProcessor.TX_CREATE_NEW, l_transactionCallback);
           
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_strNewEmpCode;
    }
    
    /**
     * (get�V�K�^�p�R�[�h) <BR>
     * �^�p�R�[�h�������̔Ԃ���B  <BR>
     *  <BR>
     * this.get�V�K�^�p�R�[�h(String,String,Date)�ɈϏ��idelegate�j����B <BR>
     *  <BR>
     * [get�V�K�^�p�R�[�h()�Ɏw�肷�����] <BR>
     * �،���ЃR�[�h�F�@@�s��.getInstitutionCode() <BR>
     * �^�p�R�[�h�s�ꎯ�ʋ敪�F�@@�s��.�^�p�R�[�h�s�ꎯ�ʋ敪 <BR>
     * �������F�@@������ <BR>
     * @@param l_market - (�s��) <BR>
     * �s��I�u�W�F�N�g
     * @@param l_datBizDate - (������)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 428467810296
     */
    public String getNewEmpCode(WEB3GentradeMarket l_market, Date l_datBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNewEmpCode(WEB3GentradeMarket l_market, " +
                "Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);        
        
        MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
        String l_strInstitutionCode = l_marketRow.getInstitutionCode();
        String l_strNewEmpCode = 
            this.getNewEmpCode(l_strInstitutionCode, l_marketRow.getFeqOrderEmpDiv(), l_datBizDate);
        log.exiting(STR_METHOD_NAME);
        return l_strNewEmpCode;
    }

    /**
     * (�O�������^�p�R�[�h�̔�TransactionCallback)<BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X<BR>
     * �i�g�����U�N�V���������FTX_JOIN_EXISTING�j<BR>
     */
    public class WEB3FeqOrderEmpCodeManageTransactionCallback implements TransactionCallback
    {
        /**
         * (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h
         */
        private String institutionCode; 
        /**
         * (�^�p�R�[�h�s�ꎯ�ʋ敪)<BR>
         * �^�p�R�[�h�s�ꎯ�ʋ敪
         */
        private String orderEmpDiv;
        /**
         * (������)<BR>
         * ������
         */
        private Date bizDate;

        /**
         * �R���X�g���N�^�B<BR>
         * <BR>
         * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
         * @@param l_strInstitutionCode
         * @@param l_strFeqOrderEmpDiv
         * @@param l_datBizDate
         */
        public WEB3FeqOrderEmpCodeManageTransactionCallback(
            String l_strInstitutionCode,
            String l_strFeqOrderEmpDiv,
            Date l_datBizDate
        ) 
        {
            this.institutionCode = l_strInstitutionCode; 
            this.orderEmpDiv = l_strFeqOrderEmpDiv;
            this.bizDate = l_datBizDate;
        }

        /**
         * �g�����U�N�V�������������{����B<BR>
         * <BR>
         * �P�j�O�������^�p�R�[�h�e�[�u�����ȉ��̏����Ō�������B<BR>
         * �@@�@@���̎��Aselect for update�ɂċ��L���b�N������<BR>
         * <BR>
         * �@@�@@[����]  <BR>
         * �@@�@@�O�������^�p�R�[�h.�،���ЃR�[�h = �،���ЃR�[�h And <BR>
         * �@@�@@�O�������^�p�R�[�h.�^�p�R�[�h�s�ꎯ�ʋ敪 =  �^�p�R�[�h�s�ꎯ�ʋ敪 And <BR>
         * �@@�@@�O�������^�p�R�[�h.������ = ������ <BR>
         * <BR>
         * �Q�j�Y���f�[�^���Ȃ��ꍇ�́A <BR>
         * �@@�@@�ȉ��̒ʂ背�R�[�h��V�K�o�^�iinsert�j���A <BR>
         * �@@�@@�^�p�R�[�h�ő�l��ԋp����B  <BR>
         * <BR>
         * �@@�@@�O�������^�p�R�[�h.�،���ЃR�[�h = �،���ЃR�[�h  <BR>
         * �@@�@@�O�������^�p�R�[�h.�^�p�R�[�h�s�ꎯ�ʋ敪 = �^�p�R�[�h�s�ꎯ�ʋ敪  <BR>
         * �@@�@@�O�������^�p�R�[�h.�^�p�R�[�h�iSEQ�j = "0001"  <BR>
         * �@@�@@�O�������^�p�R�[�h.������ = ������ <BR>
         * �@@�@@�O�������^�p�R�[�h.�쐬���� = TradingSystem.getSystemTimestamp()  <BR>
         * �@@�@@�O�������^�p�R�[�h.�X�V���� = TradingSystem.getSystemTimestamp()  <BR>
         * <BR>
         * �R�j�Y���f�[�^������ꍇ�́A�ȉ��̒ʂ背�R�[�h���X�V�iupdate�j���A <BR>
         * �@@�@@�^�p�R�[�h�ő�l��ԋp����B  <BR>
         * <BR>
         * �@@�@@�O�������^�p�R�[�h.�^�p�R�[�h�iSEQ�j = �i���P�j  <BR>
         * �@@�@@�O�������^�p�R�[�h.�X�V���� = TradingSystem.getSystemTimestamp()  <BR>
         * <BR>
         * �@@�@@�i���P�j�@@�^�p�R�[�h�iSEQ�j�̌v�Z  <BR>
         * �@@�@@�������l�Ɠ��ꔭ�����̏ꍇ  <BR>
         * �@@�@@�@@�����̔������ƁA�Y���f�[�^.�������������ꍇ�A  <BR>
         * �@@�@@�@@�����l�̒ʔԁ{�P�̐�����4���̕�����ɕҏW����B  <BR>
         * <BR>
         * �S�j�̔Ԃ����l��return����<BR>
         * <BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         */
        public Object process() throws DataNetworkException, DataQueryException
        {
            final String STR_METHOD_NAME = " process() ";
            log.entering(STR_METHOD_NAME);

            WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
                (WEB3FeqOrderEmpCodeGettingService)Services.getService(
                        WEB3FeqOrderEmpCodeGettingService.class);
            String l_strNewEmpCode = "";
            try
            {
                l_strNewEmpCode =
                    l_feqOrderEmpCodeGettingService.getPREFIX(this.institutionCode) + this.orderEmpDiv;
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "institution_code = ? and feq_order_emp_div = ? and biz_date = ?";
            String l_strCondition = " for update ";
            Object[] l_objWhereValue = new Object[3];
            l_objWhereValue[0] = this.institutionCode;
            l_objWhereValue[1] = this.orderEmpDiv;
            l_objWhereValue[2] = this.bizDate;
            List l_lstFeqOrderEmpNumber = l_processor.doFindAllQuery(
                FeqOrderEmpNumberRow.TYPE,
                l_strWhere,
                l_strCondition,
                l_objWhereValue
            );

            Timestamp l_timeStamp =
                GtlUtils.getTradingSystem().getSystemTimestamp();
            // �Q�j�Y���f�[�^���Ȃ��ꍇ�́A 
            // �@@�@@�ȉ��̒ʂ背�R�[�h��V�K�o�^�iinsert�j���A 
            // �@@�@@�^�p�R�[�h�ő�l��ԋp����B  
            if ((l_lstFeqOrderEmpNumber == null) || (l_lstFeqOrderEmpNumber.size() == 0))
            { 
                FeqOrderEmpNumberParams l_feqOrderEmpNumberParams = new FeqOrderEmpNumberParams();
                l_feqOrderEmpNumberParams.setInstitutionCode(this.institutionCode);
                l_feqOrderEmpNumberParams.setFeqOrderEmpDiv(this.orderEmpDiv);
                l_feqOrderEmpNumberParams.setLatestOrderEmpNumber("0001");
                l_feqOrderEmpNumberParams.setBizDate(this.bizDate);
                l_feqOrderEmpNumberParams.setCreatedTimestamp(l_timeStamp);
                l_feqOrderEmpNumberParams.setLastUpdatedTimestamp(l_timeStamp);
                l_processor.doInsertQuery(l_feqOrderEmpNumberParams);
                l_strNewEmpCode = l_strNewEmpCode + "0001";
            }
            // �R�j�Y���f�[�^������ꍇ�́A�ȉ��̒ʂ背�R�[�h���X�V�iupdate�j���A 
            // �@@�@@�^�p�R�[�h�ő�l��ԋp����B  
            else
            {
                FeqOrderEmpNumberParams l_feqOrderEmpNumberParams = 
                    new FeqOrderEmpNumberParams((FeqOrderEmpNumberRow)l_lstFeqOrderEmpNumber.get(0));
                    
                String str_LatestOrderEmpNumber = WEB3FeqStringUtility.addForString(l_feqOrderEmpNumberParams.getLatestOrderEmpNumber(),1);      
                l_feqOrderEmpNumberParams.setLatestOrderEmpNumber(str_LatestOrderEmpNumber);
                
                l_feqOrderEmpNumberParams.setLastUpdatedTimestamp(l_timeStamp);
                l_processor.doUpdateQuery(l_feqOrderEmpNumberParams);
                l_strNewEmpCode = l_strNewEmpCode + l_feqOrderEmpNumberParams.getLatestOrderEmpNumber();
            }                    
            log.exiting(STR_METHOD_NAME);
            return l_strNewEmpCode;
        }
    }
}
@
