head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXSingleSignOnResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �V���O���T�C���I�����X�|���X (WEB3FXSingleSignOnResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/8/25 ���Ō� (���u) �V�K�쐬   
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�V���O���T�C���I�����X�|���X) <BR>
 * �V���O���T�C���I�����X�|���X�N���X <BR>
 * 
 * @@author ���Ō�(���u)
 * @@version 1.0
 */
public class WEB3FXSingleSignOnResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_single_sign_on";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (���ʃR�[�h) <BR>
     * FX�h�L�������g�{�������`�F�b�N���ʂ��{�����𖳂��̎��ʃR�[�h <BR>
     * <BR>
     * ���V���O���T�C���I�����N�G�X�g.�d�q���`�F�b�N�t���O=false�̏ꍇ�Anull<BR>
     */
    public String[] requestCode;

    /**
     * (�����s�v���������`�F�b�N����) <BR>
     * �����s�v���������`�F�b�N���� <BR>
     * <BR>
     * true�F����L�� <BR>
     * false�F���𖳂�<BR>
     */
    public boolean noExplainAgreeHistoryCheck;

    /**
     * (�Í���������) <BR>
     * �閧���ƃ|�X�g�p�����f�[�^�ɂč쐬���ꂽ�Í��������� <BR>
     */
    public String encryptString;
    
    /**
     * (�閧��) <BR>
     * �����_���ɍ쐬���ꂽ�閧�� <BR>
     */
    public String secretKey;
    
    /**
     * (�n�b�V���l) <BR>
     * �Í���������Ɣ閧���Ő������ꂽ�n�b�V���l<BR>
     */
    public String hashValue;

    /**
     * (URL) <BR>
     * �O���ב֕ۏ؋��V�X�e��URL<BR>
     */
    public String fxUrl;
    
    /**
     * @@roseuid 41E783E3032C
     */
    public WEB3FXSingleSignOnResponse()
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FXSingleSignOnResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}@
