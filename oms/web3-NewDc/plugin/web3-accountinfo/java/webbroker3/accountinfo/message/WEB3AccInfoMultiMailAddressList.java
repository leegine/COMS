head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMultiMailAddressList.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�h���X���X�g�iWEB3AccInfoMultiMailAddressList.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2010/02/21 ���g (���u) �V�K�쐬 ���f��No.257
*/
package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����A�h���X���X�g)<BR>
 * �����A�h���X���X�g���b�Z�[�W<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AccInfoMultiMailAddressList extends Message
{

    /**
     * ���[���A�h���X���<BR>
     */
    public WEB3AccInfoMailAddressInfoUnit[] mailAddressInfoList;

    /**
     * ���[����ʏ��<BR>
     */
    public WEB3AccInfoMailAddressTypeUnit[] mailTypeInfoList;

    /**
     * (�����A�h���X���X�g)<BR>
     * �����A�h���X���X�g<BR>
     */
    public WEB3AccInfoMultiMailAddressList()
    {
    }
}
@
