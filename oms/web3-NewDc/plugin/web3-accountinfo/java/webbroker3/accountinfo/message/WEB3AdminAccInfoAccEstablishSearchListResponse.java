head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����ꗗ���X�|���X(WEB3AdminAccInfoAccEstablishSearchListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09  �����q(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����ꗗ���X�|���X)<BR>
 * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����ꗗ���X�|���X<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0 
 */

public class WEB3AdminAccInfoAccEstablishSearchListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_info_acc_establish_search_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610082152L;

    /**
     * @@roseuid 418F39F700AB
     */
    public WEB3AdminAccInfoAccEstablishSearchListResponse()
    {

    }
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR> 
     */
    public String[] branchCode;
    
    /**
     * (���҃R�[�h)<BR>
     * ���҃R�[�h<BR> 
     */
    public String traderCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR> 
     */
    public String accountCode;
    
    /**
     * (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j<BR> 
     */
    public String accountNameKana;
    
    /**
     * (�������)<BR>
     * �������<BR>
     * <BR> 
     * 0�F�@@�S��<BR>
     * 1�F�@@�l�q<BR> 
     * 2�F�@@�@@�l�q<BR>
     */
    public String accountTypeCode;
    
    /**
     * (�f�[�^���e�ԍ�)<BR>
     * �f�[�^���e�ԍ� <BR>
     * <BR>
     * 00�F�@@�f�[�^���e���I��<BR> 
     * 01�F�@@�V�K�����J�݈ē��p�f�[�^<BR> 
     * 02�F�@@�U���݃J�[�h�p�f�[�^<BR> 
     * 03�F�@@�����ڊǈē��p�f�[�^<BR>
     */
    public String dataContentDiv;
    
    /**
     * (�����J�ݓ��i���j)<BR>
     * �����J�ݓ��i���j<BR> 
     */
    public Date accountOpenDateFrom;
    
    /**
     * (�����J�ݓ��i���j)<BR>
     * �����J�ݓ��i���j<BR> 
     */
    public Date accountOpenDateTo;
    
    /**
     * (���O�C�����b�N�敪)<BR>
     * ���O�C�����b�N�敪<BR>
     * <BR>
     * 0�F�@@�S��<BR> 
     * 1�F�@@���O�C�����b�N�q<BR> 
     */
    public String loginLockDiv;
    
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
     * (�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq���)<BR>
     * �V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq���<BR>
     */
    public WEB3AccInfoAccEstablishSearchInfo[] accOpenLockSearchList;
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccInfoAccEstablishSearchListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
