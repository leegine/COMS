head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoFxAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ב֕ۏ؋��������(WEB3AccInfoFxAccountInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/2/24 䈋� (���u) �V�K�쐬
Revesion History : 2008/5/22 �Ԑi (���u) �d�l�ύX�E���f��No.234
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�ב֕ۏ؋��������) <BR>
 * �ב֕ۏ؋�������� <BR>
 * 
 * @@author 䈋�(���u)
 * @@version 1.0
 */
public class WEB3AccInfoFxAccountInfo extends Message
{
    /**
     * (FX���O�C���h�c)<BR>
     * FX���O�C���h�c
     */
    public String fxLoginId;
    
    /**
     * (FX�����ԍ��i1���ʉ݃R�[�X�j)<BR>
     * FX�����ԍ��i1���ʉ݃R�[�X�j
     */
    public String fxAccountCode1;
    
    /**
     * (FX�����ԍ��i10���ʉ݃R�[�X�j)<BR>
     * FX�����ԍ��i10���ʉ݃R�[�X�j
     */
    public String fxAccountCode2;

    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h
     */
    public String fxSystemCode;

    /**
     * (FX�������) <BR>
     * �R���X�g���N�^�B <BR>
     * 
     * @@return webbroker3.accountinfo.message.WEB3FXAccInformationUnit
     * @@roseuid 41B0393C0146
     */
    public WEB3AccInfoFxAccountInfo()
    {
    }
    
}@
