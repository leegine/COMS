head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoReadUnReadComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǌ���Comoparator(WEB3PvInfoReadUnReadComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/19 ���O�B(���u) �쐬
*/
package webbroker3.pvinfo;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3PvInfoReadFlagDef;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayMessageUnit;
import webbroker3.util.WEB3LogUtility;

/**
 * (���Ǌ���Comoparator)<BR>
 * ���Ǌ���Comparator<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoReadUnReadComparator implements Comparator
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3PvInfoReadUnReadComparator.class);

    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     */
    private String orderBy;

    /**
     * (���Ǌ���Comparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B <BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     * @@return webbroker3.pvinfo.WEB3PvInfoReadUnReadComparator
     * @@roseuid 414679400206
     */
    public WEB3PvInfoReadUnReadComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME = " WEB3PvInfoReadUnReadComparator(String)";
        log.entering(STR_METHOD_NAME );
        if(l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            String l_strErrorInfo = "�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B";
            log.error(STR_METHOD_NAME + l_strErrorInfo);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorInfo);

        }
        this.orderBy = l_strOrderBy;
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * �icompare�̎����j <BR>
     * <BR>
     * ���Ǌ��ǃt���O�̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@�����̕\�����b�Z�[�W���P�A�\�����b�Z�[�W���Q��<BR>
     * �@@�\�����b�Z�[�W���^�i�\�����b�Z�[�W���j��cast����B <BR>
     * <BR>
     * �Q�j�@@��r <BR>
     * �@@�P�j��cast�����\�����b�Z�[�W���P�A�\�����b�Z�[�W���Q�ɂ��� <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i�\�����b�Z�[�W���P.���Ǌ��ǃt���O < <BR>
     * �\�����b�Z�[�W���Q.���Ǌ��ǃt���O�j�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i�\�����b�Z�[�W���P.���Ǌ��ǃt���O = <BR>
     * �\�����b�Z�[�W���Q.���Ǌ��ǃt���O�j�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i�\�����b�Z�[�W���P.���Ǌ��ǃt���O > <BR>
     * �\�����b�Z�[�W���Q.���Ǌ��ǃt���O�j�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i�\�����b�Z�[�W���P.���Ǌ��ǃt���O < <BR>
     * �\�����b�Z�[�W���Q.���Ǌ��ǃt���O�j�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * �@@�E�i�\�����b�Z�[�W���P.���Ǌ��ǃt���O = <BR>
     * �\�����b�Z�[�W���Q.���Ǌ��ǃt���O�j�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i�\�����b�Z�[�W���P.���Ǌ��ǃt���O > <BR>
     * �\�����b�Z�[�W���Q.���Ǌ��ǃt���O�j�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * @@param l_dispMessage1 - (�\�����b�Z�[�W���1)<BR>
     * �\�����b�Z�[�W���I�u�W�F�N�g1<BR>
     * @@param l_dispMessage2 - (�\�����b�Z�[�W���2)<BR>
     * �\�����b�Z�[�W���I�u�W�F�N�g2<BR>
     * @@return int
     * @@roseuid 4146792303AC
     */
    public int compare(Object l_dispMessage1, Object l_dispMessage2)
    {

        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@������cast
        if( ! (l_dispMessage1 instanceof WEB3PvInfoDisplayMessageUnit)
            || !(l_dispMessage2 instanceof WEB3PvInfoDisplayMessageUnit))
        {
            String l_strErrorMessage =
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3PvInfoDisplayMessageUnit' �ތ^�B";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }

        WEB3PvInfoDisplayMessageUnit l_dispPvInfoMessage1 = (WEB3PvInfoDisplayMessageUnit)l_dispMessage1;
        WEB3PvInfoDisplayMessageUnit l_dispPvInfoMessage2 = (WEB3PvInfoDisplayMessageUnit)l_dispMessage2;

        //�Q�j�@@��r

        //get readFlag
        String l_strReadFlag1 = (l_dispPvInfoMessage1.readFlag) ? WEB3PvInfoReadFlagDef.READ_YES : WEB3PvInfoReadFlagDef.READ_NO;
        String l_strReadFlag2 = (l_dispPvInfoMessage2.readFlag) ? WEB3PvInfoReadFlagDef.READ_YES : WEB3PvInfoReadFlagDef.READ_NO;

        int l_intResult;
        //�Q�j�@@��r                    
        if (l_strReadFlag1 == null || l_strReadFlag2 == null)
        {
            if (l_strReadFlag1 == null && l_strReadFlag2 == null)
            {
                l_intResult = 0;
            }
            else if (l_strReadFlag1 == null)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }            
        }
        else
        {
            int l_intCompare = l_strReadFlag1.compareTo(l_strReadFlag2);
            if(l_intCompare == 0)
            {
                l_intResult = 0;                       
            }
            else if(l_intCompare < 0)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_intResult; 
    }

    /**
     * �iequals�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * false��ԋp����B <BR>
     * @@param l_dispMessage - (arg0)<BR>
     * @@return boolean
     * @@roseuid 4146792303CB
     */
    public boolean equals(Object l_dispMessage)
    {
        final String STR_METHOD_NAME = " equals(Object)";
        log.entering(STR_METHOD_NAME );

        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
