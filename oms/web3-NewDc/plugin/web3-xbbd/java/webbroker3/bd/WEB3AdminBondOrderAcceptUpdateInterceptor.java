head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderAcceptUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ�����t�X�V�C���^�Z�v�^(WEB3AdminBondOrderAcceptUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����� (���u) �V�K�쐬
*/

package webbroker3.bd;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

/**
 * (���Ǘ��Ғ�����t�X�V�C���^�Z�v�^)<BR>
 * ���Ǘ��Ғ�����t�X�V�C���^�Z�v�^<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0 
 */
public class WEB3AdminBondOrderAcceptUpdateInterceptor extends WEB3AdminBondDefaultInterceptor 
{
    
    /**
     * (���Ǘ��Ғ�����t�X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44D84FAD015E
     */
    public WEB3AdminBondOrderAcceptUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR> 
     * <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * �P�j�g�����ڃZ�b�g<BR>
     * �@@�p�����[�^.�������P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * �@@���ڐݒ���e�� �u��������t_�������P�ʃe�[�u��DB�X�V�d�l.xls�v�Q�ƁB <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44D967A0038A
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context, 
        BondOrderUnitParams l_params) 
    {
        return l_params;
    }
}
@
