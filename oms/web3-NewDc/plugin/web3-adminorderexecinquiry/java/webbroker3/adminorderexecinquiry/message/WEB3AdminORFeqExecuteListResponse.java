head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFeqExecuteListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�O�������o�����͈ꗗ���X�|���X(WEB3AdminORFeqExecuteListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�O�������o�����͈ꗗ���X�|���X)<BR>
 * �Ǘ��ҁE�O�������o�����͈ꗗ���X�|���X�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminORFeqExecuteListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_Feq_ORFeqExecuteList";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507130937L;
    
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
     * (���׈ꗗ)<BR>
     */
    public WEB3AdminORFeqExecutionUnit[] executionList;

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminORFeqExecuteListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
