head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���������ꗗ���X�|���X(WEB3AdminAccInfoInsiderInfoListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 ���C�g (���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҂��q�l���������ꗗ���X�|���X)<BR>
 * �Ǘ��҂��q�l���������ꗗ���X�|���X<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_InsiderInfoList";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412211207L;
    
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
     * (�����ҏ��ꗗ)<BR>
     * ���������i���X�R�[�h�A�ڋq�R�[�h�A�����R�[�h�j<BR>
     * �ɍ��v���A����ʕ\���\�[�g���𔽉f���A���y�[�W���\���s���𖳎������A<BR>
     * ���Y��ʕ\���ΏۂƂȂ�f�[�^�̑S�����̃��X�g<BR>
     */
    public WEB3AccInfoInsiderInfoUnit[] insideInfoList;
    
    /**
     * (�Ǘ��҂��q�l�������ҏ��ꗗ���X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     */
    public WEB3AdminAccInfoInsiderInfoListResponse (WEB3GenRequest l_request)
    {
        super(l_request);
    }
    
}
@
