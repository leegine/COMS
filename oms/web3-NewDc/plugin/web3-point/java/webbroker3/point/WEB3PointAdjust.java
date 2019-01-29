head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.01.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointAdjust.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g����(WEB3PointAdjust.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/30 ���w�� (���u) �V�K�쐬
*/
package webbroker3.point;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.point.data.PointAdjustParams;

/**
 * (�|�C���g����)<BR>
 * �|�C���g�����N���X<BR>
 * 
 * @@author ���w��
 * @@version 1.0 
 */
public class WEB3PointAdjust implements BusinessObject 
{
    
    /**
     * (�|�C���g�����s)<BR>
     * �|�C���g�����s�I�u�W�F�N�g<BR>
     */
    private PointAdjustParams pointAdjustParams;
    
    /**
     * (�|�C���g����)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j��̃|�C���g����Params�I�u�W�F�N�g�𐶐����Athis.�|�C���g�����s�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�j�������Athis.�|�C���g�����s�̊e���ڂɃZ�b�g����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@param l_intAdjustPoint - (�����|�C���g)<BR>
     * �����|�C���g<BR>
     * 
     * @@roseuid 419477A60000
     */
    public WEB3PointAdjust(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, int l_intAdjustPoint) 
    {
        //�P�j��̃|�C���g����Params�I�u�W�F�N�g�𐶐����Athis.�|�C���g�����s�ɃZ�b�g����B
        PointAdjustParams l_params = new PointAdjustParams();
        
        l_params.setInstitutionCode(l_strInstitutionCode);
        l_params.setBranchCode(l_strBranchCode);
        l_params.setAccountCode(l_strAccountCode);
        l_params.setAdjustPoint(l_intAdjustPoint);
        
        //�Q�j�������Athis.�|�C���g�����s�̊e���ڂɃZ�b�g����B
        this.pointAdjustParams = l_params;
    }
    
    /**
     * this.�|�C���g�����s��ԋp����B<BR>
     * @@return Object
     * @@roseuid 41947AF603D8
     */
    public Object getDataSourceObject() 
    {
        return this.pointAdjustParams;
    }
}@
