head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenHostAccRegVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq�o�^�`�[�i�f�P�P�j�L���[(WEB3AccOpenHostAccRegVoucher.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/15 ���G�� (���u) �V�K�쐬
*/
package webbroker3.accountopen;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.accountopen.data.HostAccRegVoucherDao;
import webbroker3.accountopen.data.HostAccRegVoucherParams;
import webbroker3.accountopen.data.HostAccRegVoucherRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ڋq�o�^�`�[�i�f�P�P�j�L���[)<BR>
 * �ڋq�o�^�`�[�i�f�P�P�j�L���[<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AccOpenHostAccRegVoucher 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenHostAccRegVoucher.class);
    
    /**
     * (�ڋq�o�^�`�[�i�f11�j�L���[�s)<BR>
     * �ڋq�o�^�`�[�i�f11�j�L���[�s<BR>
     * <BR>
     * �ڋq�o�^�`�[�i�f11�j�L���[Params�N���X��DDL��莩����������B<BR>
     */
    private HostAccRegVoucherParams hostAccRegVoucherParams;
    
    /**
     * (�ڋq�o�^�`�[�i�f�P�P�j�L���[ )<BR>
     * �ڋq�o�^�`�[�i�f�P�P�j�L���[�I�u�W�F�N�g���擾����B<BR> 
     * <BR>
     * �ȉ��̏����Ōڋq�o�^�`�[�i�f�P�P�j�L���[�e�[�u������������B<BR> 
     * <BR>
     * [����] <BR>
     * �ڋq�o�^�`�[�i�f�P�P�j�L���[.���ʃR�[�h = ����.���ʃR�[�h <BR>
     * �ڋq�o�^�`�[�i�f�P�P�j�L���[.�،���ЃR�[�h = ����.�،���ЃR�[�h<BR> 
     * �ڋq�o�^�`�[�i�f�P�P�j�L���[.���X�R�[�h = ����.���X�R�[�h <BR>
     * �ڋq�o�^�`�[�i�f�P�P�j�L���[.�ڋq�R�[�h = ����.�ڋq�R�[�h <BR>
     * �ڋq�o�^�`�[�i�f�P�P�j�L���[.�f�[�^�R�[�h = ����.�f�[�^�R�[�h <BR>
     * <BR>
     * �������ʂ̌ڋq�o�^�`�[�i�f�P�P�j�L���[�s�I�u�W�F�N�g�� <BR>
     * this.�ڋq�o�^�`�[�i�f�P�P�j�L���[�s�ɃZ�b�g����B<BR>
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_strRequestCode - (�f�[�^�R�[�h)<BR>
     * �f�[�^�R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public WEB3AccOpenHostAccRegVoucher(
        String l_strOrderRequestNumber, 
        String l_strInstitutionCode, 
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strRequestCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3AccOpenHostAccRegVoucher(" +
            "String, String, String, String ,String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            // [����]
            //�ڋq�o�^�`�[�i�f�P�P�j�L���[.���ʃR�[�h = ����.���ʃR�[�h              
            // �ڋq�o�^�`�[�i�f�P�P�j�L���[.�،���ЃR�[�h = ����.�،���ЃR�[�h            
            // �ڋq�o�^�`�[�i�f�P�P�j�L���[.���X�R�[�h = ����.���X�R�[�h 
            // �ڋq�o�^�`�[�i�f�P�P�j�L���[.�ڋq�R�[�h = ����.�ڋq�R�[�h 
            // �ڋq�o�^�`�[�i�f�P�P�j�L���[.�f�[�^�R�[�h = ����.�f�[�^�R�[�h   
            
            HostAccRegVoucherRow l_row = 
                (HostAccRegVoucherRow)
                    HostAccRegVoucherDao.findRowByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode(
                        l_strOrderRequestNumber, 
                        l_strRequestCode, 
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);
            if (l_row == null)
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
            
            //�������ʂ̌ڋq�o�^�`�[�i�f�P�P�j�L���[�s�I�u�W�F�N�g�� 
            //this.�ڋq�o�^�`�[�i�f�P�P�j�L���[�s�ɃZ�b�g����B
            this.hostAccRegVoucherParams = new HostAccRegVoucherParams(l_row);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);   
        } 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�M���o�R�敪)<BR>
     * �M���o�R�敪���擾����B<BR> 
     * <BR>
     * this.�ڋq�o�^�`�[�i�f�P�P�j�L���[�s.�M���o�R�敪��ԋp����B<BR>
     * @@return String
     */
    public String getTrustViaDiv()
    {
        final String STR_METHOD_NAME = "getTrustViaDiv()";
        log.entering(STR_METHOD_NAME);
        
        //�M���o�R�敪���擾����B
        //this.�ڋq�o�^�`�[�i�f�P�P�j�L���[�s.�M���o�R�敪��ԋp����B
        String l_strTrustViaDiv = this.hostAccRegVoucherParams.getTrustViaDiv();
        
        log.exiting(STR_METHOD_NAME);
        return l_strTrustViaDiv;
    }
    
    /**
     * (get�敨OP�����J�݋敪�i���؁j)<BR>
     * �敨OP�����J�݋敪�i���؁j���擾����B<BR> 
     * <BR>
     * this.�ڋq�o�^�`�[�i�f�P�P�j�L���[�s.�敨OP�����J�݋敪�i���؁j��ԋp����B<BR>
     * @@return String
     */
    public String getIfoAccOpenDivTokyo()
    {
        final String STR_METHOD_NAME = "getIfoAccOpenDivTokyo()";
        log.entering(STR_METHOD_NAME);
        
        //�敨OP�����J�݋敪�i���؁j���擾����B
        //this.�ڋq�o�^�`�[�i�f�P�P�j�L���[�s.�敨OP�����J�݋敪�i���؁j��ԋp����B
        String l_strIfoAccOpenDivTokyo = 
            this.hostAccRegVoucherParams.getIfoAccOpenDivTokyo();
        
        log.exiting(STR_METHOD_NAME);
        return l_strIfoAccOpenDivTokyo;
    }
    
    /**
     * (get�敨OP�����J�݋敪�i��؁j)<BR>
     * �敨OP�����J�݋敪�i��؁j���擾����B<BR> 
     * <BR>
     * this.�ڋq�o�^�`�[�i�f�P�P�j�L���[�s.�敨OP�����J�݋敪�i��؁j��ԋp����B<BR>
     * @@return String
     */
    public String getIfoAccOpenDivOsaka()
    {
        final String STR_METHOD_NAME = "getIfoAccOpenDivOsaka()";
        log.entering(STR_METHOD_NAME);
        
        //�敨OP�����J�݋敪�i��؁j���擾����B
        //this.�ڋq�o�^�`�[�i�f�P�P�j�L���[�s.�敨OP�����J�݋敪�i��؁j��ԋp����B
        String l_strIfoAccOpenDivOsaka = 
            this.hostAccRegVoucherParams.getIfoAccOpenDivOsaka();
        
        log.exiting(STR_METHOD_NAME);
        return l_strIfoAccOpenDivOsaka;
    }
    
    /**
     * (get�敨OP�����J�݋敪�i���؁j)<BR>
     * �敨OP�����J�݋敪�i���؁j���擾����B<BR> 
     * <BR>
     * this.�ڋq�o�^�`�[�i�f�P�P�j�L���[�s.�敨OP�����J�݋敪�i���؁j��ԋp����B<BR>
     * @@return String
     */
    public String getIfoAccOpenDivNagoya()
    {
        final String STR_METHOD_NAME = "getIfoAccOpenDivNagoya()";
        log.entering(STR_METHOD_NAME);
        
        //�敨OP�����J�݋敪�i���؁j���擾����B
        //this.�ڋq�o�^�`�[�i�f�P�P�j�L���[�s.�敨OP�����J�݋敪�i���؁j��ԋp����B
        String l_strIfoAccOpenDivNagoya = 
            this.hostAccRegVoucherParams.getIfoAccOpenDivNagoya();
        
        log.exiting(STR_METHOD_NAME);
        return l_strIfoAccOpenDivNagoya;
    }
    
    /**
     * (get�f�[�^�R�[�h)<BR>
     * �f�[�^�R�[�h<BR>
     *<BR>
     * @@return String
     */
    public String getRequestCode()
    {
        final String STR_METHOD_NAME = "getRequestCode()";
        log.entering(STR_METHOD_NAME);
        
        //�f�[�^�R�[�h���擾����B
        String l_strRequestCode = this.hostAccRegVoucherParams.getRequestCode();
        
        log.exiting(STR_METHOD_NAME);
        return l_strRequestCode;
    }
    
    /**
     * (get�����J�݂P_�ی�a��)<BR>
     * �����J�݂P(�ی�a��)<BR> 
     * <BR>
     * @@return String
     */
    public String getAccountOpenDiv1()
    {
        final String STR_METHOD_NAME = "getAccountOpenDiv1()";
        log.entering(STR_METHOD_NAME);
        
        //�����J�݂P(�ی�a��)���擾����B
        String l_strAccountOpenDiv1 = 
            this.hostAccRegVoucherParams.getAccountOpenDiv1();
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountOpenDiv1;
    }
    
    /**
     * (get�����J�݂Q_�ϗ�����)<BR>
     * �����J�݂Q�i�ϗ������j<BR> 
     * <BR>
     * @@return String
     */
    public String getAccountOpenDiv2()
    {
        final String STR_METHOD_NAME = "getAccountOpenDiv2()";
        log.entering(STR_METHOD_NAME);
        
        //�����J�݂Q�i�ϗ������j���擾����B
        String l_strAccountOpenDiv2 = 
            this.hostAccRegVoucherParams.getAccountOpenDiv2();
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountOpenDiv2;
    }
    
    /**
     * (get�����J�݂R_�M�p���)<BR>
     * �����J�݂R�i�M�p����j<BR> 
     * <BR>
     * @@return String
     */
    public String getAccountOpenDiv3()
    {
        final String STR_METHOD_NAME = "getAccountOpenDiv3()";
        log.entering(STR_METHOD_NAME);
        
        //�����J�݂R�i�M�p����j���擾����B
        String l_strAccountOpenDiv3 = 
            this.hostAccRegVoucherParams.getAccountOpenDiv3();
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountOpenDiv3;
    }
    
    /**
     * (get�����J�݂T_�O���،�)<BR>
     * �����J�݂T�i�O���،��j<BR> 
     * <BR>
     * @@return String
     */
    public String getAccountOpenDiv5()
    {
        final String STR_METHOD_NAME = "getAccountOpenDiv5()";
        log.entering(STR_METHOD_NAME);
        
        //�����J�݂T�i�O���،��j���擾����B
        String l_strAccountOpenDiv5 = 
            this.hostAccRegVoucherParams.getAccountOpenDiv5();
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountOpenDiv5;
    }
    
    /**
     * (get�����J�݂P�P_�����I�v�V����)<BR>
     * �����J�݂P�P�i�����I�v�V�����j<BR> 
     * <BR>
     * @@return String
     */
    public String getAccountOpenDiv11()
    {
        final String STR_METHOD_NAME = "getAccountOpenDiv11()";
        log.entering(STR_METHOD_NAME);
        
        //�����J�݂P�P�i�����I�v�V�����j���擾����B
        String l_strAccountOpenDiv11 = 
            this.hostAccRegVoucherParams.getAccountOpenDiv11();
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountOpenDiv11;
    }
    
    /**
     * @@return Object
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return this.hostAccRegVoucherParams;   
    }
}
@
