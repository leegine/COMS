head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuoteCheckService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ��������_�`�F�b�N�T�[�r�X�C���^�[�t�F�[�X(WEB3QuoteCheckService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2009/02/03 �� (FLJ)�V�K�쐬
 */

package webbroker3.quotecheck.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.quotecheck.message.WEB3QuoteCheckRequest;
import webbroker3.quotecheck.message.WEB3QuoteCheckResponse;

/**
 * �i��������_�`�F�b�N�T�[�r�X�C���^�[�t�F�[�X�j�B
 * @@version 1.0
 */
public interface WEB3QuoteCheckService
    extends Service
{
    public WEB3QuoteCheckResponse execute(WEB3QuoteCheckRequest l_request) throws
        WEB3BaseException;
}
@
