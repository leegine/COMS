head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiCommCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�萔������(WEB3SrvRegiCommCond.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 �s�p (���u) �V�K�쐬
*/

package webbroker3.srvregi;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.data.SrvRegiCommCondParams;
import webbroker3.srvregi.data.SrvRegiCommCondRow;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�萔������)<BR>
 * �T�[�r�X���p�萔�������N���X<BR>
 * 
 * @@author �s�p
 * @@version 1.0
 */

public class WEB3SrvRegiCommCond implements BusinessObject
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3SrvRegiCommCond.class);

    /**
     * (�T�[�r�X���p�萔�������s)
     */
    private SrvRegiCommCondParams srvCommCondParams;

    /**
     * (�T�[�r�X���p�萔������)<BR>
     * �R���X�g���N�^<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strSrvDiv - �T�[�r�X�敪
     * @@param l_strOrderAccProduct - ������t���i
     * @@roseuid 4186E39F0339
     */
    protected WEB3SrvRegiCommCond(String l_strInstitutionCode, String l_strSrvDiv, String l_strOrderAccProduct) 
    {
        this.srvCommCondParams = new SrvRegiCommCondParams();
        this.srvCommCondParams.setInstitutionCode(l_strInstitutionCode);
        this.srvCommCondParams.setSrvDiv(l_strSrvDiv);
        this.srvCommCondParams.setOrderAccProduct(l_strOrderAccProduct);     
    }
    
    /**
     * �X�V�s�pParams�̃N���[���s�𐶐����ĕԋp����B<BR>
     * <BR>
     * �@@this.�T�[�r�X���p�萔�������s���R�s�[���āA�������e��<BR>
     * �ʃC���X�^���X���쐬����iclone�j�B<BR>
     * �쐬�����R�s�[�����g��this.�T�[�r�X���p�萔�������s�ɃZ�b�g����B<BR>
     * @@roseuid 4186E3C1023F
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams() ";
        log.entering(STR_METHOD_NAME);

        SrvRegiCommCondParams l_srvCommCondParams = new SrvRegiCommCondParams(this.srvCommCondParams);
        this.srvCommCondParams = l_srvCommCondParams;
        
        log.exiting(STR_METHOD_NAME);          
    }
    
    /**
     * �igetDataSourceObject�̎����j<BR> 
     * <BR>
     * this.�T�[�r�X���p�萔�������s��ԋp����B<BR>
     * @@return Object
     * @@roseuid 4186E3C1025E
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return this.srvCommCondParams;
    }
    
    /**
     * (get������t���i)<BR>
     * this.�T�[�r�X���p�萔�������}�X�^�[�s.get������t���i( )��<BR>
     * �߂�l��ԋp����B<BR>
     * @@return String
     * @@roseuid 4187002F0370
     */
    public String getOrderAccProduct() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.srvCommCondParams.getOrderAccProduct();
    }
    
    /**
     * (createNew�T�[�r�X���p�萔������)<BR>
     * �T�[�r�X���p�萔�������I�u�W�F�N�g��V�K�ɐ�������B<BR>
     * <BR>
     * �|�T�[�r�X���p�萔�������̃R���X�g���N�^���R�[�����A�擾����<BR>
     * �@@�T�[�r�X���p�萔�������I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strSrvDiv - �T�[�r�X�敪
     * @@param l_strOrderAccProduct - ������t���i
     * @@return �T�[�r�X���p.�i�T�[�r�X���p�j���i�G���e�B�e�B.WEB3SrvRegiCommCondition
     * @@roseuid 4186E3920126
     */
    public static WEB3SrvRegiCommCond createNewSrvRegiCommCondition(String l_strInstitutionCode, String l_strSrvDiv, String l_strOrderAccProduct) 
    {
        final String STR_METHOD_NAME = " createNewSrvRegiCommCondition(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiCommCond l_srvCommCondition = 
            new WEB3SrvRegiCommCond(l_strInstitutionCode, l_strSrvDiv, l_strOrderAccProduct);
        
        log.exiting(STR_METHOD_NAME); 
        
        return l_srvCommCondition;
    }
    
    /**
     * (saveNew�T�[�r�X���p�萔������)<BR>
     * this.�T�[�r�X���p�萔�������s�̓��e�ŁA<BR>
     * �T�[�r�X���p�萔�������e�[�u���ɐV�K�s��INSERT����B<BR>
     * <BR>
     * 1) this.�T�[�r�X���p�萔�������s�̈ȉ��̍��ڂɒl���Z�b�g����B<BR>
     * �@@�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )<BR>
     * �@@�쐬���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 2) this.�T�[�r�X���p�萔�������s�̓��e�ŁA<BR>
     * �T�[�r�X���p�萔�������e�[�u��<BR>
     * �@@��INSERT�������s���B<BR>
     * @@roseuid 4186E408002C
     */
    public void saveNewSrvRegiCommCondition() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewSrvRegiCommCondition() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) this.�T�[�r�X���p�萔�������s�̈ȉ��̍��ڂɒl���Z�b�g����B
            this.srvCommCondParams.setLastUpdater(
                WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode());
        
            this.srvCommCondParams.setCreatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            
            this.srvCommCondParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            
            //2) this.�T�[�r�X���p�萔�������s�̓��e�ŁA�T�[�r�X���p�萔�������e�[�u��
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_queryProcessor.doInsertQuery(this.srvCommCondParams);//DataNetworkException, DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
            
        log.exiting(STR_METHOD_NAME);                    
    }
    
    /**
     * (remove�T�[�r�X���p�萔����)<BR>
     * �T�[�r�X���p�萔�������e�[�u����蓖�Y�s���폜����B<BR>
     * <BR>
     * 1) �ȉ��̏����ŃT�[�r�X���p�萔�������e�[�u���ɍ폜�������s���B<BR>
     * [�폜����]<BR>
     * �@@�،���ЃR�[�h=this.�،���ЃR�[�h<BR>
     * �@@�T�[�r�X�敪=this.�T�[�r�X�敪<BR>
     * �@@������t���i=this.������t���i<BR>
     * <BR>
     * 2) this.�T�[�r�X���p�萔�������s�̃C���X�^���X��j������B<BR>
     * @@roseuid 4186E439004B
     */
    public void removeSrvRegiCommCondition() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " removeSrvRegiCommCondition() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) �ȉ��̏����ŃT�[�r�X���p�萔�������e�[�u������������B
            String l_strWhere = " institution_code = ? and srv_div = ? and order_acc_product = ? ";
                
            Object[] l_obj = {this.srvCommCondParams.getInstitutionCode(),
                this.srvCommCondParams.getSrvDiv(),
                this.srvCommCondParams.getOrderAccProduct()};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_queryProcessor.doDeleteAllQuery(SrvRegiCommCondRow.TYPE, l_strWhere, l_obj);//DataNetworkException, DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);    
    }
}
@
