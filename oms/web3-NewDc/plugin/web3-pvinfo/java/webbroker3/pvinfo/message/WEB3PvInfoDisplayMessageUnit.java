head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.06.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoDisplayMessageUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �\�����b�Z�[�W���(WEB3PvInfoDisplayMessageUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
*/
package webbroker3.pvinfo.message;

import java.util.Date;


/**
 * (�\�����b�Z�[�W���)<BR>
 * �\�����b�Z�[�W���N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoDisplayMessageUnit extends WEB3PvInfoDisplayContentsUnit 
{
    
    /**
     * (�{������ID)<BR>
     * �{������ID<BR>
     */    
    public String browseHistoryId;
    
    /**
     * (�\�����b�Z�[�W������)<BR>
     * �\�����b�Z�[�W������<BR>
     */
    public Date displayMessageDate;
    
    /**
     * (���Ǌ��ǃt���O)<BR>
     * ���Ǌ��ǃt���O<BR>
     * <BR>
     * false�F�@@����<BR>
     * true�F�@@����<BR>
     */
    public boolean readFlag;
    
    /**
     * (�\�����b�Z�[�W���)<BR>
     * �R���X�g���N�^�B<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoDisplayMessageUnit
     * @@roseuid 4146714B03BB
     */
    public WEB3PvInfoDisplayMessageUnit() 
    {
     
    }
}
@
