head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.01.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApply.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�\��(WEB3PointApply.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/30 ���w�� (���u) �V�K�쐬
*/
package webbroker3.point;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.point.data.PointApplyParams;
import webbroker3.util.WEB3LogUtility;


/**
 * (�|�C���g�\��)<BR>
 * �|�C���g�\���N���X<BR>
 * 
 * @@author ���w��
 * @@version 1.0 
 */
public class WEB3PointApply implements BusinessObject 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PointApply.class);
    
    /**
     * (�\���s)<BR>
     * �|�C���g�\���s�I�u�W�F�N�g<BR>
     */
    private PointApplyParams pointApplyParams;
    
    /**
     * (�|�C���g�\��)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �������Athis.�\���s�ɃZ�b�g����B<BR>
     * @@param l_pointApplyParams - (�\���s)<BR>
     * �|�C���g�\���s�I�u�W�F�N�g<BR>
     * 
     * @@roseuid 41A44953039A
     */
    public WEB3PointApply(PointApplyParams l_pointApplyParams) 
    {
        this.pointApplyParams = l_pointApplyParams;
    }
    
    /**
     * (�|�C���g�\��)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j��̃|�C���g�\��Params�I�u�W�F�N�g�𐶐����Athis.�\���s�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�j�������Athis.�\���s�̊e���ڂɃZ�b�g����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * 
     * @@param l_strPremiumNo - (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     * 
     * @@param l_intUsedPoint - (�g�p�|�C���g)<BR>
     * �g�p�|�C���g<BR>
     * 
     * @@roseuid 41A449530392
     */
    public WEB3PointApply(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strPremiumNo, int l_intUsedPoint) 
    {
        final String STR_METHOD_NAME = " WEB3PointApply(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        PointApplyParams l_params = new PointApplyParams();
        
        //�،���ЃR�[�h
        l_params.setInstitutionCode(l_strInstitutionCode);
        
        //���X�R�[�h
        l_params.setBranchCode(l_strBranchCode);
        
        //�����R�[�h
        l_params.setAccountCode(l_strAccountCode);
        
        //�i�i�ԍ�
        l_params.setPremiumNo(l_strPremiumNo);
        
        //�g�p�|�C���g
        l_params.setUsedPoint(l_intUsedPoint);
        
        this.pointApplyParams = l_params;
        
        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * (get�\��ID)<BR>
     * �\��ID���擾����B<BR>
     * <BR>
     * this.�\���s.�\��ID��ԋp����B<BR>
     * @@return long
     * @@roseuid 419C97340372
     */
    public long getApplyId() 
    {
        return this.pointApplyParams.getApplyId();
    }
    
    /**
     * (get���X�R�[�h)<BR>
     * ���X�R�[�h���擾����B<BR>
     * <BR>
     * this.�\���s.���X�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 419C8F5D01FB
     */
    public String getBranchCode() 
    {
        return this.pointApplyParams.getBranchCode();
    }
    
    /**
     * (get�����R�[�h)<BR>
     * �����R�[�h���擾����B<BR>
     * <BR>
     * this.�\���s.�����R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 419C9BC401BC
     */
    public String getAccountCode() 
    {
        return this.pointApplyParams.getAccountCode();
    }
    
    /**
     * (get�i�i�ԍ�)<BR>
     * �i�i�ԍ����擾����B<BR>
     * <BR>
     * this.�\���s.�i�i�ԍ���ԋp����B<BR>
     * @@return String
     * @@roseuid 419C95320324
     */
    public String getPremiumNo() 
    {
        return this.pointApplyParams.getPremiumNo();
    }
    
    /**
     * (get�\������)<BR>
     * �\���������擾����B<BR>
     * <BR>
     * this.�\���s.�\��������ԋp����B<BR>
     * @@return Date
     * @@roseuid 419C9756020A
     */
    public Date getApplyTimestamp() 
    {
        return this.pointApplyParams.getApplyTimestamp();
    }
    
    /**
     * (get�\����t�敪)<BR>
     * �\����t�敪���擾����B<BR>
     * <BR>
     * this.�\���s.�\����t�敪��ԋp����B<BR>
     * @@return String
     * @@roseuid 419C9779013F
     */
    public String getApplyAcceptDiv() 
    {
        return this.pointApplyParams.getApplyAcceptDiv();
    }
    
    /**
     * this.�\���s��ԋp����B<BR>
     * @@return Object
     * @@roseuid 419C8EAC02A7
     */
    public Object getDataSourceObject() 
    {
        return this.pointApplyParams;
    }
    
    /**
     * this.�\���s���R�s�[���āA�������e�̕ʃC���X�^���X���쐬����iclone�j�B<BR>
     * �쐬�����R�s�[�����g��this.�\���s�ɃZ�b�g����B<BR>
     * @@roseuid 419C90D901CC
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        PointApplyParams l_params = new PointApplyParams(this.pointApplyParams);
        this.pointApplyParams = l_params;
        
        log.exiting(STR_METHOD_NAME); 
    }
}
@
