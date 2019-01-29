head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExpirationDateListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����L�������擾���X�|���X(WEB3ExpirationDateListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/14 ���n(���u) �V�K�쐬���f��319
*/

package webbroker3.gentrade.message;


import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����L�������擾���X�|���X)<BR>
 * �����L�������擾���X�|���X�N���X<BR>
 *
 * @@author ���n
 * @@version 1.0
 */
public class WEB3ExpirationDateListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "expiration_date_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802141630L;

    /**
     * (���������敪�ꗗ)<BR>
     * ���������敪�ꗗ<BR>
     */
    public String[] expirationDateTypeList;

    /**
     * (�L�������J�n��)<BR>
     * �L�������J�n��<BR>
     */
    public Date expirationStartDate;

    /**
     * (�L�������ŏI��)<BR>
     * �L�������ŏI��<BR>
     */
    public Date expirationEndDate;

    /**
     * (�L���������j���ꗗ)<BR>
     * �L���������j���ꗗ<BR>
     */
    public Date[] holidayList;

    /**
     * (����敪)<BR>
     * �[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j�@@NULL�F���̑�<BR>
     */
    public String sessionType;

    /**
     * @@roseuid 47B3E1310232
     */
    public WEB3ExpirationDateListResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3ExpirationDateListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
