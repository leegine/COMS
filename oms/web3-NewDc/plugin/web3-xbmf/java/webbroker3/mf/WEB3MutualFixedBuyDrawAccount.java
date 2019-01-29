head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyDrawAccount.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �莞��z���t��������(WEB3MutualFixedBuyDrawAccount.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/24 �h�C (���u) �V�K�쐬 
*/
package webbroker3.mf;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mf.data.MfFixedBuyingDrawAccountParams;
import webbroker3.mf.data.MfFixedBuyingDrawAccountRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�莞��z���t��������)<BR>
 *  �莞��z���t��������
 * @@author �h�C(���u)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyDrawAccount implements BusinessObject
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyDrawAccount.class);
    
    /**
     * (�莞��z���t���������s)<BR>
     * �莞��z���t���������s�I�u�W�F�N�g <BR>
     */
    private MfFixedBuyingDrawAccountParams mfFixedBuyingDrawAccountParams;
    
    /**
     * (get���Z�@@�֋敪)<BR>
     * ���Z�@@�֋敪��Ԃ��B<BR>
     * this.getDataSourceObject().get���Z�@@�֋敪()�̖߂�l��Ԃ��B<BR>
     * @@return String<BR>
     */
    public String getFinInstitutionDiv()
    {
        return ((MfFixedBuyingDrawAccountParams)this.getDataSourceObject()).getFinInstitutionDiv();
    }
    
    /**
     * (get��s�R�[�h)<BR>
     * ��s�R�[�h��Ԃ��B<BR>
     * this.getDataSourceObject().get��s�R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String<BR>
     */
    public String getFinInstitutionCode()
    {
        return ((MfFixedBuyingDrawAccountParams)this.getDataSourceObject()).getFinInstitutionCode();
    }
    
    /**
     * (get�x�X�R�[�h)<BR>
     * �x�X�R�[�h��Ԃ��B<BR>
     * this.getDataSourceObject().get�x�X�R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String<BR>
     */
    public String getFinBranchCode()
    {
        return ((MfFixedBuyingDrawAccountParams)this.getDataSourceObject()).getFinBranchCode();
    }
    
    /**
     * (get�a���敪 )<BR>
     * �a���敪��Ԃ��B<BR>
     * this.getDataSourceObject().get�a���敪()�̖߂�l��Ԃ��B<BR>
     * @@return String<BR>
     */
    public String getDepositDiv()
    {
        return ((MfFixedBuyingDrawAccountParams)this.getDataSourceObject()).getDepositDiv();
    }
    
    /**
     * (get���������ԍ�)<BR>
     * ���������ԍ���Ԃ��B<BR>
     * this.getDataSourceObject().get���������ԍ�()�̖߂�l��Ԃ��B<BR>
     * @@return String<BR>
     */
    public String getDrawAccountNo()
    {
        return ((MfFixedBuyingDrawAccountParams)this.getDataSourceObject()).getDrawAccountNo();
    }
    
    /**
     * (get�����������`�l�i�J�i�j)<BR>
     * �����������`�l�i�J�i�j��Ԃ��B<BR>
     * this.getDataSourceObject().get�����������`�l�i�J�i�j()�̖߂�l��Ԃ��B<BR>
     * @@return String<BR>
     */
    public String getDrawAccountNameKana()
    {
        return ((MfFixedBuyingDrawAccountParams)this.getDataSourceObject()).getDrawAccountNameKana();
    }
    
    /**
     * (�莞��z���t��������)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA�莞��z���t���������e�[�u������������B  <BR>
     * <BR>
     * �@@�@@�m���������n <BR>
     * �@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �@@�@@�@@���X�R�[�h�@@= ����.���X�R�[�h <BR>
     * �@@�@@�@@�����R�[�h�@@= ����.�����R�[�h <BR>
     * <BR>
     * �Q�j�擾���ꂽ���R�[�h��this.�莞��z���t���������s�ɃZ�b�g����B <BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public WEB3MutualFixedBuyDrawAccount(
        String l_strInstitutionCode,
        String l_strBranchCode, 
        String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "WEB3MutualFixedBuyDrawAccount(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�ȉ��̏����ŁA�莞��z���t���������e�[�u������������B
        //�m���������n 
        //�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@�@@���X�R�[�h�@@= ����.���X�R�[�h
        //�@@�@@�����R�[�h�@@= ����.�����R�[�h 
        List l_lisRow = new ArrayList();
        String l_strCondition =  
            " institution_code = ? and branch_code = ? and account_code = ? ";
        Object[] l_objConditionValue = new Object[] {
            l_strInstitutionCode, 
            l_strBranchCode, 
            l_strAccountCode};
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRow = l_queryProcessor.doFindAllQuery(
                MfFixedBuyingDrawAccountRow.TYPE, 
                l_strCondition, 
                l_objConditionValue);            
        }
        catch(DataNetworkException l_dnex)
           {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
           }        
        catch(DataQueryException l_dqex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        
        if(l_lisRow == null || l_lisRow.size() == 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���!"); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME
            );
        }
        
        //�Q�j�擾���ꂽ���R�[�h��this.�莞��z���t���������s�ɃZ�b�g����B
        MfFixedBuyingDrawAccountRow l_row = (MfFixedBuyingDrawAccountRow)l_lisRow.get(0);
        this.mfFixedBuyingDrawAccountParams = new MfFixedBuyingDrawAccountParams(l_row);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * BusinessObject.getDataSourceObject()<BR>
     * this.mfFixedBuyingDrawAccountParams��Ԃ��B<BR>
     * @@return Object <BR>
     */
    public Object getDataSourceObject() 
    {
        return this.mfFixedBuyingDrawAccountParams;
    }
}
@
