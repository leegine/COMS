head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�\���ݒ�ꗗ���X�|���X(WEB3AdminPvInfoConditionListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/25 ������(���u) �쐬
Revesion History : 2005/12/8 杊��](���u) �d�l�ύXNo.059�C��
*/
package webbroker3.pvinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�\���ݒ�ꗗ���X�|���X)<BR>
 * �Ǘ��ҁE�\���ݒ�ꗗ���X�|���X�N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionListResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_PvInfo_conditionList";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (�\���������ꗗ)<BR>
     */
    public WEB3PvInfoDisplayConditionUnit[] displayConditionUnits;
    
    /**
     * (�\�����e���ꗗ)<BR>
     */
    public WEB3PvInfoDisplayContentsUnit[] displayContentsUnits;
    
    /**
     * (���y�[�W��)<BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     */
    public String totalRecords;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     */
    public String pageIndex;

    /**
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminPvInfoConditionListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
