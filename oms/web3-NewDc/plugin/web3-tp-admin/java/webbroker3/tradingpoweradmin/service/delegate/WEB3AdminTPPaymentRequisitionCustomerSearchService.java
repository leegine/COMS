head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionCustomerSearchService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ڋq�����T�[�r�X(WEB3AdminTPPaymentRequisitionCustomerSearchService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/06 ���z(���u) �V�K�쐬 ���f��No.027
*/

package webbroker3.tradingpoweradmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (���������ڋq�����T�[�r�X)<BR>
 * <BR>
 * @@author ���z
 * @@version 1.0
 */
public interface WEB3AdminTPPaymentRequisitionCustomerSearchService extends WEB3BusinessService
{
    /**
     * ���������ڋq�����T�[�r�X�������s���B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}@
