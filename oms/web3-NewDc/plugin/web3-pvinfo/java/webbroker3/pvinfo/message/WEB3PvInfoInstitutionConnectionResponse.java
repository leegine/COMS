head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.04.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoInstitutionConnectionResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،���ИA�����X�|���X(WEB3PvInfoInstitutionConnectionResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/20 ���O�B(���u) �쐬
*/
package webbroker3.pvinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�،���ИA�����X�|���X)<BR>
 * �،���ИA�����X�|���X�N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoInstitutionConnectionResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PvInfo_institutionConnection";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (�،���Ж�)<BR>
     * �،���Ж�<BR>
     */
    public String institutionName;
    
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
     * (�\�����e���ꗗ)<BR>
     * �\�����e���ꗗ<BR>
     */
    public WEB3PvInfoDisplayContentsUnit[] displayContentsUnits;
    
    /**
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3PvInfoInstitutionConnectionResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
