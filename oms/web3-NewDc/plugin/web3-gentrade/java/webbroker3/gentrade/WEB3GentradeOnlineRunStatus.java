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
filename	WEB3GentradeOnlineRunStatus.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �I�����C�����s����(WEB3GentradeOnlineRunStatus)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/5/6 ���Ō� (���u) �V�K�쐬
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.gentrade.data.OnlineRunStatusDao;
import webbroker3.gentrade.data.OnlineRunStatusParams;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�I�����C�����s����)<BR>
 * ���ו��U���s���I�����C�������̎��s���ʂ��Ǘ�����B<BR>
 * <BR>
 * �iDB���C�A�E�g �u�I�����C�����s���ʃe�[�u���d�l.xls�v�Q�Ɓj<BR>
 * 
 * @@author ���Ō�
 * @@version 1.0
 */
public class WEB3GentradeOnlineRunStatus implements BusinessObject 
{
    /**
     * ���O
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3GentradeOnlineRunStatus.class);
    
    /**
     * �I�����C�����s����Row�I�u�W�F�N�g�B
     */
    private OnlineRunStatusRow onlineRunStatusRow;
    
    /**
     * (�I�����C�����s����)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �{�I�u�W�F�N�g���C���X�^���X�����A<BR>
     * ������Row�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_onlineRunStatusRow - �I�����C�����s����Row�I�u�W�F�N�g
     * @@return WEB3GentradeOnlineRunStatus
     * @@roseuid 42798D330347
     */
    public WEB3GentradeOnlineRunStatus(OnlineRunStatusRow l_onlineRunStatusRow) 
    {
        this.onlineRunStatusRow = l_onlineRunStatusRow;
    }
    
    /**
     * this.�I�����C�����s����Row��ԋp����B
     * @@return Object
     * @@roseuid 42798D330328
     */
    public Object getDataSourceObject() 
    {
        return this.onlineRunStatusRow;
    }
    
    /**
     * (set������)<BR>
     * �istatic���\�b�h�j<BR>
     * �w����e�ŃI�����C�����s���ʃe�[�u�����������A<BR>
     * �u�������v�X�e�[�^�X�łP���R�[�h�̓o�^�i�f�[�^�Ȃ����j�^<BR>
     * �X�V�i�f�[�^���莞�j���s���A<BR>
     * �o�^�^�X�V���R�[�h�ɊY������I�����C�����s���ʃI�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�w����e�ŁA�I�����C�����s���ʃe�[�u������������B<BR>
     * <BR>
     * �@@-----------------------------------------------------<BR>
     * �@@������������<BR>
     * <BR>
     * �@@�@@�@@�،���ЃR�[�h = �����̏،���ЃR�[�h<BR>
     * �@@���@@�����^�C�v = �����̖����^�C�v<BR>
     * �@@���@@�敨�^�I�v�V�����敪 = �����̐敨�^�I�v�V�����敪<BR>
     * �@@���@@�I�����C���T�[�r�X�敪 = �����̃I�����C���T�[�r�X�敪<BR>
     * �@@���@@From����ID = ������From����ID<BR>
     * �@@-----------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�Y���f�[�^�̗L���ɂ��A�ȉ��̒ʂ蕪�򂷂�B<BR>
     * <BR>
     * �Q�|�P�j�@@�Y���f�[�^�Ȃ��̏ꍇ<BR>
     * <BR>
     * �@@�E�w����e�ŁA�I�����C�����s���ʃe�[�u���ɂP���R�[�h�o�^����B<BR>
     * <BR>
     * �@@�@@-----------------------------------------------------<BR>
     * �@@�@@���I�����C�����s���ʃe�[�u���F�o�^���e��<BR>
     * <BR>
     * �@@�@@�،���ЃR�[�h�F�@@�����̏،���ЃR�[�h<BR>
     * �@@�@@�����^�C�v�F�@@�����̖����^�C�v<BR>
     * �@@�@@�敨�^�I�v�V�����敪�F�@@�����̐敨�^�I�v�V�����敪<BR>
     * �@@�@@�I�����C���T�[�r�X�敪�F�@@�����̃I�����C���T�[�r�X�敪<BR>
     * �@@�@@From����ID�F�@@������From����ID<BR>
     * �@@�@@To����ID�F�@@������To����ID<BR>
     * �@@�@@�����敪�F�@@"������"<BR>
     *     �쐬���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     *     �X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * �@@�@@-----------------------------------------------------<BR>
     * <BR>
     * �@@�E�o�^���R�[�h�̃I�����C�����s����Row�I�u�W�F�N�g���w�肵�A<BR>
     * �@@�@@�I�����C�����s���ʃI�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@���������I�����C�����s���ʃI�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �Q�|�Q�j�@@�Y���f�[�^����̏ꍇ<BR>
     * <BR>
     * �@@�E�擾�������R�[�h.���s�X�e�[�^�X�敪 = "������"�̏ꍇ�́A<BR>
     * �@@�@@�u�w��AP�N�����i��d�N���G���[�j�v�̗�O��throw����B<BR>
     *�@@�@@�@@tag: BUSINESS_ERROR_01992
     *�@@�@@�@@class: WEB3BusinessLayerException
     * <BR>
     * �@@�E�擾�������R�[�h.���s�X�e�[�^�X�敪 !=  "������"�̏ꍇ�́A<BR>
     *�@@�@@�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�i�P�j�������ʂ̃I�����C�����s����Row�I�u�W�F�N�g���w�肵�A<BR>
     * �@@�@@�@@�@@�@@�I�����C�����s���ʃI�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�i�Q�j���������I�����C�����s���ʃI�u�W�F�N�g.<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@update���s�X�e�[�^�X�敪("������")�����s����B<BR>
     * �@@�@@�i�R�j���������I�����C�����s���ʃI�u�W�F�N�g��ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h�B
     * @@param l_productType - �����^�C�v�B
     * @@param l_strFutureOptionDiv - �敨�^�I�v�V�����敪�B<BR>
     * �i0�FDEFAULT�@@1�F�敨�@@2�F�I�v�V�����j<BR>
     * @@param l_strOnlineServiceDiv - �I�����C���T�[�r�X�敪�B<BR>
     * �i1�F�o���I���ʒm�@@2�F�����J�z�j<BR>
     * @@param l_lngAccountIdFrom - From����ID�B
     * @@param l_lngAccountIdTo - To����ID�B
     * @@return webbroker3.gentrade.WEB3GentradeOnlineRunStatus
     * @@roseuid 42799B2E00FD
     */
    public static WEB3GentradeOnlineRunStatus setDealing(
            String l_strInstitutionCode, 
            ProductTypeEnum l_productType, 
            String l_strFutureOptionDiv, 
            String l_strOnlineServiceDiv, 
            long l_lngAccountIdFrom, 
            long l_lngAccountIdTo)
                throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setDealing";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeOnlineRunStatus l_result = null;
//        �P�j�@@DB����
//       �@@�w����e�ŁA�I�����C�����s���ʃe�[�u������������B
//
//       �@@-----------------------------------------------------
//       �@@������������
//
//       �@@�@@�@@�،���ЃR�[�h = �����̏،���ЃR�[�h
//       �@@���@@�����^�C�v = �����̖����^�C�v
//       �@@���@@�敨�^�I�v�V�����敪 = �����̐敨�^�I�v�V�����敪
//       �@@���@@�I�����C���T�[�r�X�敪 = �����̃I�����C���T�[�r�X�敪
//       �@@���@@From����ID = ������From����ID
//       �@@-----------------------------------------------------
        
        boolean l_blnIsDataExist = false;
        OnlineRunStatusRow l_onlineRunStatusRow = null;
        try
        {
            try
            {
                l_onlineRunStatusRow =
                    (OnlineRunStatusRow)OnlineRunStatusDao.findRowByInstitutionCodeProductTypeFutureOptionDivOnlineServiceDivAccountIdFrom(
                        l_strInstitutionCode,
                        l_productType,
                        l_strFutureOptionDiv,
                        l_strOnlineServiceDiv,
                        l_lngAccountIdFrom);
            }
            catch (DataFindException l_dfex)
            {
                l_blnIsDataExist = false;
                
                log.debug("Data not found  from online_run_status with " + 
                        " �،���ЃR�[�h =  " + l_strInstitutionCode + 
                        " �����^�C�v = " + l_productType + 
                        " �敨�^�I�v�V�����敪 = " + l_strFutureOptionDiv + 
                        " �I�����C���T�[�r�X�敪 = " + l_strOnlineServiceDiv + 
                        " From����ID = " + l_lngAccountIdFrom);
            }
                        
            if (l_onlineRunStatusRow == null)
            {
                l_blnIsDataExist = false;
            }
            else
            {
                l_blnIsDataExist = true;
            }
                        
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            if (!l_blnIsDataExist)
            {
                //�Q�|�P�j�@@�Y���f�[�^�Ȃ��̏ꍇ
                
                //�E�w����e�ŁA�I�����C�����s���ʃe�[�u���ɂP���R�[�h�o�^����B
                OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
                
//              �@@�@@-----------------------------------------------------
//                
//              �@@�@@���I�����C�����s���ʃe�[�u���F�o�^���e��
//              �@@�@@�،���ЃR�[�h�F�@@�����̏،���ЃR�[�h
//              �@@�@@�����^�C�v�F�@@�����̖����^�C�v
//              �@@�@@�敨�^�I�v�V�����敪�F�@@�����̐敨�^�I�v�V�����敪
//              �@@�@@�I�����C���T�[�r�X�敪�F�@@�����̃I�����C���T�[�r�X�敪
//              �@@�@@From����ID�F�@@������From����ID
//              �@@�@@To����ID�F�@@������To����ID
//              �@@�@@�����敪�F�@@"������"
//                  �쐬���t�F�@@GtlUtils.getSystemTimestamp()
//                  �X�V���t�F�@@GtlUtils.getSystemTimestamp()
//              �@@�@@-----------------------------------------------------
                l_onlineRunStatusParams.setInstitutionCode(l_strInstitutionCode);
                l_onlineRunStatusParams.setProductType(l_productType);
                l_onlineRunStatusParams.setFutureOptionDiv(l_strFutureOptionDiv);
                l_onlineRunStatusParams.setOnlineServiceDiv(l_strOnlineServiceDiv);
                l_onlineRunStatusParams.setAccountIdFrom(l_lngAccountIdFrom);
                l_onlineRunStatusParams.setAccountIdTo(l_lngAccountIdTo);
                l_onlineRunStatusParams.setRunStatusDiv(WEB3RunStatusDivDef.DEALING);    
                l_onlineRunStatusParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_onlineRunStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                l_queryProcessor.doInsertQuery(l_onlineRunStatusParams);
                
//               �@@�E�o�^���R�[�h�̃I�����C�����s����Row�I�u�W�F�N�g���w�肵�A
//              �@@�@@�I�����C�����s���ʃI�u�W�F�N�g�𐶐�����B
//              �@@�@@���������I�����C�����s���ʃI�u�W�F�N�g��ԋp����B
                l_result = new WEB3GentradeOnlineRunStatus(l_onlineRunStatusParams);
                
            }
            else
            {
                //�Y���f�[�^����̏ꍇ
                
                if (WEB3RunStatusDivDef.DEALING.equals(l_onlineRunStatusRow.getRunStatusDiv()))
                {
                    //�E�擾�������R�[�h.���s�X�e�[�^�X�敪 = "������"�̏ꍇ�́A
                    //�u�w��AP�N�����i��d�N���G���[�j�v�̗�O��throw����B
                    throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                            STR_METHOD_NAME);                    
                }
                else
                {
                    //�擾�������R�[�h.���s�X�e�[�^�X�敪 != "������"�̏ꍇ�́A�ȉ��̏������s���B
                    //�i�P�j�������ʂ̃I�����C�����s����Row�I�u�W�F�N�g���w�肵�A
                    //    �I�����C�����s���ʃI�u�W�F�N�g�𐶐�����B
                    l_result = new WEB3GentradeOnlineRunStatus(l_onlineRunStatusRow);
                    
                    //�i�Q�j���������I�����C�����s���ʃI�u�W�F�N�g.update���s�X�e�[�^�X�敪("������")�����s����B
                    l_result.updateRunStatusDiv(WEB3RunStatusDivDef.DEALING);
                                     
                }                                
            }
        }
        catch (DataQueryException l_dfex)
        {
            log.error(STR_METHOD_NAME, l_dfex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_dfex.getMessage(),
                l_dfex);
        }        
        catch (DataNetworkException l_dfex)
        {
            log.error(STR_METHOD_NAME, l_dfex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_dfex.getMessage(),
                l_dfex);
        }
            
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (update���s�X�e�[�^�X�敪)<BR>
     * �I�u�W�F�N�g�v���p�e�B��Row�ɊY������I�����C�����s���ʃe�[�u���̃��R�[�h��<BR>
     * ���s�X�e�[�^�X�敪���A�w��l�ōX�V����B<BR>
     * <BR>
     * �P�j�v���p�e�B��Row�I�u�W�F�N�g�ɊY������I�����C�����s���ʃe�[�u���̂P���R�[�h��<BR>
     * �@@�@@�@@�X�V����B<BR>
     * <BR>
     * �@@-----------------------------------------------------<BR>
     * �@@���I�����C�����s���ʃe�[�u���F�X�V�l�ݒ���e��<BR>
     * <BR>
     * �@@���s�X�e�[�^�X�敪�F�@@�����̎��s�X�e�[�^�X�敪<BR>
     * �@@�X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * �@@-----------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@return����B<BR>
     * @@param l_strRunStatusDiv - ���s�X�e�[�^�X�敪�B<BR>
     * �i0�F�������@@1�F�����ρ@@5�F�������@@9�F�G���[�j<BR>
     * @@roseuid 4279A1BB0354
     */
    public void updateRunStatusDiv(String l_strRunStatusDiv) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateRunStatusDiv(String l_strRunStatusDiv) ";
        log.entering(STR_METHOD_NAME);
        
        //�v���p�e�B��Row�I�u�W�F�N�g�ɊY������I�����C�����s����Params�I�u�W�F�N�g�𐶐�
        OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams(this.onlineRunStatusRow);
        
        //�X�V�l�ݒ���e
        //���s�X�e�[�^�X�敪�F�@@�����̎��s�X�e�[�^�X�敪
        l_onlineRunStatusParams.setRunStatusDiv(l_strRunStatusDiv);
        //�X�V���t�F�@@GtlUtils.getSystemTimestamp()
        l_onlineRunStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        this.onlineRunStatusRow = l_onlineRunStatusParams;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            l_queryProcessor.doUpdateQuery(l_onlineRunStatusParams);
        }
        catch (DataQueryException l_dfex)
        {
            log.error(STR_METHOD_NAME, l_dfex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfex.getMessage(),
                l_dfex);
        }        
        catch (DataNetworkException l_dfex)
        {
            log.error(STR_METHOD_NAME, l_dfex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfex.getMessage(),
                l_dfex);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
