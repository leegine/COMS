head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�����J�ݓ��̓��X�|���X(WEB3FXAccOpenInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 ���z (���u) �V�K�쐬
Revesion History : 2008/09/22 ���g (���u) �d�l�ύX�E���f��1013
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX�����J�ݓ��̓��X�|���X) <BR>
 * FX�����J�ݓ��̓��X�|���X�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXAccOpenInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_acc_open_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (���[���A�h���X) <BR>
     * WEB3����p�ɓo�^�ς̃��[���A�h���X <BR>
     */
    public String mailAddress;

    /**
     * (���ʃR�[�h) <BR>
     * �d�q������{�������Ȃ��Ɣ��f���ꂽ���ʃR�[�h�B <BR>
     */
    public String[] requestCode;

    /**
     * (GFT�����J�݃t���O)<BR>
     * GFT�����J�݃t���O<BR>
     */
    public boolean gftAccOpenFlag;

    /**
     * @@roseuid 41E783E3032C
     */
    public WEB3FXAccOpenInputResponse()
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FXAccOpenInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}@
