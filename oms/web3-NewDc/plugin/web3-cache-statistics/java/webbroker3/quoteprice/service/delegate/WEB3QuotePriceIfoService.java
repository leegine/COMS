head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.09.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePriceIfoService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �敨OP�����I�l�ۑ��T�[�r�X�C���^�[�t�F�[�X(WEB3QuotePriceService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/5/18 ��(FLJ) �V�K�쐬
 */

package webbroker3.quoteprice.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.*;
import webbroker3.common.*;
import webbroker3.quoteprice.message.*;

/**
 * �i�敨OP�����I�l�ۑ��T�[�r�X�C���^�[�t�F�[�X�j�B
 * @@version 1.0
 */
public interface WEB3QuotePriceIfoService
    extends Service
{
    public WEB3QuotePriceSaveResult execute(WEB3QuotePriceSaveRequest l_request) throws
        WEB3BaseException;
}
@
