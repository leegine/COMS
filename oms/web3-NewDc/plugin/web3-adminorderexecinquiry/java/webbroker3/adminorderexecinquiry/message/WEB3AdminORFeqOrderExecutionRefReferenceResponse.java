head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFeqOrderExecutionRefReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�O�������������Ɖ�X�|���X(WEB3AdminORFeqOrderExecutionRefReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�O�������������Ɖ�X�|���X)<BR>
 * �Ǘ��ҁE�O�������������Ɖ�X�|���X�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminORFeqOrderExecutionRefReferenceResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_Feq_ORFeqOrderExecutionRefReference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507130937L;
    
    /**
     * (�o���I���^�����J�z�����敪)<BR>
     * �o���I���^�����J�z�����敪<BR>
     * <BR>
     * 0�F�@@�o���I����<BR>
     * 1�F�@@�����J�z������<BR>
     * 2�F�@@�����J�z������<BR>
     * 9�F�@@�����J�z�����G���[<BR>
     */
    public String carryoverEndType = null;
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * (�ڍ׉�ʏ��ꗗ)<BR>
     */
    public WEB3AdminORDetailDispInfoUnit detailDispInfoList[];
    
    /**
     * (�Ǘ��ҊO�������������Ɖ�Unit�ꗗ)<BR>
     */
    public WEB3AdminORFeqOrderExecutionRefUnit feqOrderExecuteList[];
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminORFeqOrderExecutionRefReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
