head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCfdAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : CFD�������iWEB3AccInfoCfdAccountInfo.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/26 ���g (���u) �V�K�쐬 ���f��No.250
*/
package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (CFD�������)<BR>
 * CFD�������<BR>
 *
 * @@author ���g(���u)
 * @@version 1.0
 */
public class WEB3AccInfoCfdAccountInfo extends Message
{

    /**
     * (CFD���O�C��ID)<BR>
     * CFD���O�C��ID<BR>
     */
    public String cfdLoginId;

    /**
     * (CFD�����ԍ�)<BR>
     * CFD�����ԍ��iCFD�R�[�X�j<BR>
     */
    public String cfdAccountCode;

    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     */
    public String fxSystemCode;

    /**
     * 
     */
    public WEB3AccInfoCfdAccountInfo()
    {

    }
}
@
