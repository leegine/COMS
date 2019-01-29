head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenRequestNumberServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݎ��ʃR�[�h�̔ԃT�[�r�XImpl(WEB3AccOpenRequestNumberServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 �s�p (���u) �V�K�쐬
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.accountopen.data.AccOpenRequestNumberParams;
import webbroker3.accountopen.data.AccOpenRequestNumberRow;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRequestNumberService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����J�ݎ��ʃR�[�h�̔ԃT�[�r�XImpl)<BR>
 * �����J�ݎ��ʃR�[�h�̔ԃT�[�r�X�����N���X<BR>
 * 
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AccOpenRequestNumberServiceImpl implements WEB3AccOpenRequestNumberService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccOpenRequestNumberServiceImpl.class);

    /**
     * @@roseuid 41B45E730251
     */
    public WEB3AccOpenRequestNumberServiceImpl() 
    {
     
    }
    
    /**
     * (get�V�K���ʃR�[�h)<BR>
     * �����J�݌����q�̎��ʃR�[�h�������̔Ԃ���B<BR>
     * <BR>
     * ���ʃR�[�h�̃R�[�h�̌n�͈ȉ��̒ʂ�B<BR>
     * <BR>
     * �@@�E�P�`�W���ځiindex=0�`7�j�F�@@���t�Z�N�V�����iyyyyMMdd�j<BR>
     * �@@�E�X�`�P�R���ځiindex=8�`12�j�F�@@5���̒ʔ�<BR>
     * �@@�i�،���Ж��C�����J�ݎ��ʃR�[�h�e�[�u���ɍŏI�̔Ԓl��ۑ��j<BR>
     * <BR>
     * �P�j�@@���t�Z�N�V�����i�hyyyyMMdd�h�擾�j<BR>
     * �@@�@@TradingSystem.getBizDate() ���hyyyyMMdd�h�Ƀt�H�[�}�b�g����<BR>
     * ��������擾����B<BR>
     * <BR>
     * �Q�j�@@���ʃR�[�h�e�[�u�����X�V����B<BR>
     * <BR>
     * �@@�����J�ݎ��ʃR�[�h�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�����J�ݎ��ʃR�[�h.�،���ЃR�[�h = �،���ЃR�[�h<BR>
     * <BR>
     * �@@�|�Y���f�[�^���Ȃ��ꍇ�́A�ȉ��̒ʂ背�R�[�h��V�K�o�^�iinsert�j���A<BR>
     * ���ʃR�[�h�ő�l��ԋp����B<BR>
     * <BR>
     * �@@�@@�����J�ݎ��ʃR�[�h.�،���ЃR�[�h = �،���ЃR�[�h<BR>
     * �@@�@@�����J�ݎ��ʃR�[�h.���ʃR�[�h�ő�l = yyyyMMdd + "00001"<BR>
     * �@@�@@�����J�ݎ��ʃR�[�h.�쐬���� = TradingSystem.getSystemTimestamp()<BR>
     * �@@�@@�����J�ݎ��ʃR�[�h.�X�V���� = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �@@�|�Y���f�[�^������ꍇ�́A�ȉ��̒ʂ背�R�[�h���X�V�iupdate�j���A<BR>
     * ���ʃR�[�h�ő�l��ԋp����B<BR>
     * <BR>
     * �@@�@@�����J�ݎ��ʃR�[�h.���ʃR�[�h�ő�l = yyyyMMdd + �i���P�j<BR>
     * �@@�@@�����J�ݎ��ʃR�[�h.�X�V���� = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �@@�@@�i���P�j�@@���ʃR�[�h�ő�l�i�ʔԕ����j�̌v�Z<BR>
     * <BR>
     * �@@�@@���@@�����l�Ɠ�����̏ꍇ<BR>
     * �@@�@@�@@�P�j�Ŏ擾�������t�Z�N�V����������ƁA�����l�̓��t�Z�N�V����������<BR>
     * �����ꍇ�A<BR>
     * �@@�@@�@@�����l�̒ʔԁ{�P�̐�����5���̕�����ɕҏW����B<BR>
     * <BR>
     * �@@�@@���@@�����l�Ɠ�����łȂ��ꍇ<BR>
     * �@@�@@�@@"00001"��ҏW����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h�B
     * @@return String
     * @@roseuid 41871B5E0080
     */
    public String getNewRequestNumber(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNewRequestNumber(String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);        

        try
        {
            //�P�j�@@���t�Z�N�V�����i�hyyyyMMdd�h�擾�j 
            //TradingSystem.getBizDate() ���hyyyyMMdd�h�Ƀt�H�[�}�b�g������������擾����
            TradingSystem l_tradingSystem = GtlUtils.getTradingSystem();
            String l_strDate = WEB3DateUtility.formatDate(l_tradingSystem.getBizDate(), "yyyyMMdd");
        
            //�Q�j�@@���ʃR�[�h�e�[�u�����X�V����B
            String l_strWhere = " institution_code = ? ";
                
            Object[] l_obj = {l_strInstitutionCode};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisRequestNumberRowList = null;

            l_lisRequestNumberRowList = l_queryProcessor.doFindAllQuery(
                AccOpenRequestNumberRow.TYPE, 
                l_strWhere,
                " FOR UPDATE ", 
                l_obj);//DataNetworkException, DataQueryException
                    
            int l_intRowCnt = 0;
            
            if (l_lisRequestNumberRowList != null)
            {
                l_intRowCnt = l_lisRequestNumberRowList.size();
            }
            
            AccOpenRequestNumberRow l_row = null;
            
            if (l_intRowCnt == 1)
            {
                l_row = (AccOpenRequestNumberRow)l_lisRequestNumberRowList.get(0);
            }
            else if (l_intRowCnt > 1)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
           
            String l_strLasterRequestNo = "";
           
            //�Y���f�[�^���Ȃ��ꍇ�́A�ȉ��̒ʂ背�R�[�h��V�K�o�^�iinsert�j���A
            //���ʃR�[�h�ő�l��ԋp����B
            if (l_row == null)
            {
                log.debug("l_row == null");
                
                AccOpenRequestNumberParams l_params = new AccOpenRequestNumberParams();
                //�����J�ݎ��ʃR�[�h.�،���ЃR�[�h = �،���ЃR�[�h
                l_params.setInstitutionCode(l_strInstitutionCode);
               
                l_strLasterRequestNo = l_strDate + "00001";
               
                //�����J�ݎ��ʃR�[�h.���ʃR�[�h�ő�l = yyyyMMdd + "00001"
                l_params.setLastAccOpenRequestNumber(l_strLasterRequestNo);
               
                //�����J�ݎ��ʃR�[�h.�쐬���� = TradingSystem.getSystemTimestamp()
                l_params.setCreatedTimestamp(l_tradingSystem.getSystemTimestamp());
               
                //�����J�ݎ��ʃR�[�h.�X�V���� = TradingSystem.getSystemTimestamp()
                l_params.setLastUpdatedTimestamp(l_tradingSystem.getSystemTimestamp());
               
                l_queryProcessor.doInsertQuery(l_params);//DataNetworkException, DataQueryException
            }
            //�Y���f�[�^������ꍇ�́A�ȉ��̒ʂ背�R�[�h���X�V�iupdate�j���A<BR>
            //���ʃR�[�h�ő�l��ԋp����
            else
            {
                log.debug("l_row != null");
                
                AccOpenRequestNumberParams l_params = new AccOpenRequestNumberParams(l_row);
               
                String l_strLasterRequestNoBefore = l_params.getLastAccOpenRequestNumber();
                String l_strDateDB = l_strLasterRequestNoBefore.substring(0,8);
               
                if (l_strDateDB.equals(l_strDate))
                {
                    log.debug("same day");
                    
                    String l_strNo = l_strLasterRequestNoBefore.substring(8);
                    l_strLasterRequestNo = 
                        l_strDateDB + WEB3StringTypeUtility.formatNumber(Long.parseLong(l_strNo) + 1, 5);
                }
                else
                {
                    log.debug("not same day");
                    
                    l_strLasterRequestNo = l_strDate + "00001";
                }
                            
                //�����J�ݎ��ʃR�[�h.���ʃR�[�h�ő�l = yyyyMMdd + �i���P�j
                l_params.setLastAccOpenRequestNumber(l_strLasterRequestNo);
               
                //�����J�ݎ��ʃR�[�h.�X�V���� = TradingSystem.getSystemTimestamp()
                l_params.setLastUpdatedTimestamp(l_tradingSystem.getSystemTimestamp());
               
                l_queryProcessor.doUpdateQuery(l_params);//DataNetworkException, DataQueryException
            }
           
            log.exiting(STR_METHOD_NAME);
            return l_strLasterRequestNo;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
}
@
