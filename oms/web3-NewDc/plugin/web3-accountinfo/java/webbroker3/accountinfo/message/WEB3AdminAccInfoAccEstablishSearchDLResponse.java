head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.07.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchDLResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����޳�۰��ڽ��ݽ�(WEB3AdminAccInfoAccEstablishSearchDLResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09 �����q(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�N���X �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����޳�۰��ڽ��ݽ�)<BR>
 * �N���X �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����޳�۰��ڽ��ݽ�<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0 
 */

public class WEB3AdminAccInfoAccEstablishSearchDLResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_info_acc_establish_search_dl";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610082152L;

    /**
     * @@roseuid 418F39F700AB
     */
    public WEB3AdminAccInfoAccEstablishSearchDLResponse()
    {

    }
    
    /**
     * (�_�E�����[�h�t�@@�C��)<BR>
     * �_�E�����[�h�t�@@�C��<BR> 
     * <BR>
     * �� CSV�t�@@�C���s�̔z��<BR> 
     */
    public String[] downloadFile;
    
    /**
     * (���ݓ���)<BR>
     * ���ݓ���<BR> 
     */
    public Date currentDate;
    
    /**
     * (�f�[�^���e�ԍ�)<BR>
     * �f�[�^���e�ԍ�<BR> 
     * <BR>
     * 00�F�@@�f�[�^���e���I��<BR> 
     * 01�F�@@�V�K�����J�݈ē��p�f�[�^<BR> 
     * 02�F�@@�U���݃J�[�h�p�f�[�^<BR> 
     * 03�F�@@�����ڊǈē��p�f�[�^<BR>
     */
    public String dataContentNumber;
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccInfoAccEstablishSearchDLResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
